package org.binance.client.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.binance.client.exceptions.BinanceClientException;
import org.binance.client.exceptions.BinanceConnectorException;
import org.binance.client.exceptions.BinanceServerException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ResponseHandler {
    private static final OkHttpClient client = HttpClientSingleton.getHttpClient();
    private static final int HTTP_STATUS_CODE_400 = 400;
    private static final int HTTP_STATUS_CODE_499 = 499;
    private static final int HTTP_STATUS_CODE_500 = 500;

    private ResponseHandler() {
    }

    public static String handleResponse(Request request, boolean showLimitUsage) {
        try {
            Response response = client.newCall(request).execute();
            Throwable var3 = null;

            String var5;
            try {
                if (null == response) {
                    throw new BinanceServerException("[ResponseHandler] No response from server");
                }

                String responseAsString = getResponseBodyAsString(response.body());
                if (response.code() >= 400 && response.code() <= 499) {
                    throw handleErrorResponse(responseAsString, response.code());
                }

                if (response.code() >= 500) {
                    throw new BinanceServerException(responseAsString, response.code());
                }

                if (showLimitUsage) {
                    var5 = getlimitUsage(response, responseAsString);
                    return var5;
                }

                var5 = responseAsString;
            } catch (Throwable var16) {
                var3 = var16;
                throw var16;
            } finally {
                if (response != null) {
                    if (var3 != null) {
                        try {
                            response.close();
                        } catch (Throwable var15) {
                            var3.addSuppressed(var15);
                        }
                    } else {
                        response.close();
                    }
                }

            }

            return var5;
        } catch (IllegalStateException | IOException var18) {
            throw new BinanceConnectorException("[ResponseHandler] OKHTTP Error: " + var18.getMessage());
        }
    }

    private static String getlimitUsage(Response response, String resposeBodyAsString) {
        JSONObject json = new JSONObject();
        json.put("x-mbx-used-weight", response.header("x-mbx-used-weight"));
        json.put("x-mbx-used-weight-1m", response.header("x-mbx-used-weight-1m"));
        json.put("data", resposeBodyAsString);
        return json.toString();
    }

    private static BinanceClientException handleErrorResponse(String responseBody, int responseCode) {
        try {
            String errorMsg = JSONParser.getJSONStringValue(responseBody, "msg");
            int errorCode = JSONParser.getJSONIntValue(responseBody, "code");
            return new BinanceClientException(responseBody, errorMsg, responseCode, errorCode);
        } catch (JSONException var4) {
            throw new BinanceClientException(responseBody, responseCode);
        }
    }

    private static String getResponseBodyAsString(ResponseBody body) throws IOException {
        return null != body ? body.string() : "";
    }
}
