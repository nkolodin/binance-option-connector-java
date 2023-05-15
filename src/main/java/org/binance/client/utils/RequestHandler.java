package org.binance.client.utils;

import okhttp3.Request;
import org.binance.client.enums.HttpMethod;
import org.binance.client.enums.RequestType;
import org.binance.client.exceptions.BinanceConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public class RequestHandler {
    private final String apiKey;
    private final String secretKey;
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public RequestHandler(String apiKey) {
        this.apiKey = apiKey;
        this.secretKey = null;
    }

    public RequestHandler(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    private String sendApiRequest(String baseUrl, String urlPath, String signature, LinkedHashMap<String, Object> parameters, HttpMethod httpMethod, RequestType requestType, boolean showLimitUsage) {
        String fullUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters, signature);
        logger.info("{} {}", httpMethod, fullUrl);
        Request request;
        switch (requestType) {
            case PUBLIC:
                request = RequestBuilder.buildPublicRequest(fullUrl, httpMethod);
                break;
            case WITH_API_KEY:
            case SIGNED:
                request = RequestBuilder.buildApiKeyRequest(fullUrl, httpMethod, this.apiKey);
                break;
            default:
                throw new BinanceConnectorException("[RequestHandler] Invalid request type: " + requestType);
        }

        return ResponseHandler.handleResponse(request, showLimitUsage);
    }

    public String sendPublicRequest(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters, HttpMethod httpMethod, boolean showLimitUsage) {
        return this.sendApiRequest(baseUrl, urlPath, (String)null, parameters, httpMethod, RequestType.PUBLIC, showLimitUsage);
    }

    public String sendWithApiKeyRequest(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters, HttpMethod httpMethod, boolean showLimitUsage) {
        if (null != this.apiKey && !this.apiKey.isEmpty()) {
            return this.sendApiRequest(baseUrl, urlPath, (String)null, parameters, httpMethod, RequestType.WITH_API_KEY, showLimitUsage);
        } else {
            throw new BinanceConnectorException("[RequestHandler] API key cannot be null or empty!");
        }
    }

    public String sendSignedRequest(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters, HttpMethod httpMethod, boolean showLimitUsage) {
        if (null != this.secretKey && !this.secretKey.isEmpty() && null != this.apiKey && !this.apiKey.isEmpty()) {
            parameters.put("timestamp", UrlBuilder.buildTimestamp());
            String queryString = UrlBuilder.joinQueryParameters(parameters);
            String signature = SignatureGenerator.getSignature(queryString, this.secretKey);
            return this.sendApiRequest(baseUrl, urlPath, signature, parameters, httpMethod, RequestType.SIGNED, showLimitUsage);
        } else {
            throw new BinanceConnectorException("[RequestHandler] Secret key/API key cannot be null or empty!");
        }
    }
}
