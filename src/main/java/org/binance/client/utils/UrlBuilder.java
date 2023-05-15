package org.binance.client.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UrlBuilder {
    private static final int MAX_DECIMAL_DIGITS = 30;
    private static DecimalFormat df;

    private UrlBuilder() {
    }

    public static String buildFullUrl(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters, String signature) {
        if (parameters != null && !parameters.isEmpty()) {
            StringBuilder sb = new StringBuilder(baseUrl);
            sb.append(urlPath).append('?');
            joinQueryParameters(sb, parameters);
            if (null != signature) {
                sb.append("&signature=").append(signature);
            }

            return sb.toString();
        } else {
            return baseUrl + urlPath;
        }
    }

    public static String buildStreamUrl(String baseUrl, ArrayList<String> streams) {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("?streams=");
        return joinStreamUrls(sb, streams);
    }

    public static String joinQueryParameters(LinkedHashMap<String, Object> parameters) {
        return joinQueryParameters(new StringBuilder(), parameters).toString();
    }

    public static StringBuilder joinQueryParameters(StringBuilder urlPath, LinkedHashMap<String, Object> parameters) {
        if (parameters != null && !parameters.isEmpty()) {
            boolean isFirst = true;
            Iterator var3 = parameters.entrySet().iterator();

            while(true) {
                while(var3.hasNext()) {
                    Map.Entry<String, Object> mapElement = (Map.Entry)var3.next();
                    if (mapElement.getValue() instanceof Double) {
                        parameters.replace(mapElement.getKey(), getFormatter().format(mapElement.getValue()));
                    } else if (mapElement.getValue() instanceof ArrayList) {
                        if (!((ArrayList)mapElement.getValue()).isEmpty()) {
                            String key = (String)mapElement.getKey();
                            joinArrayListParameters(key, urlPath, (ArrayList)mapElement.getValue(), isFirst);
                            isFirst = false;
                        }
                        continue;
                    }

                    if (isFirst) {
                        isFirst = false;
                    } else {
                        urlPath.append('&');
                    }

                    urlPath.append((String)mapElement.getKey()).append('=').append(urlEncode(mapElement.getValue().toString()));
                }

                return urlPath;
            }
        } else {
            return urlPath;
        }
    }

    private static void joinArrayListParameters(String key, StringBuilder urlPath, ArrayList<?> values, boolean isFirst) {
        Object value;
        for(Iterator var4 = values.iterator(); var4.hasNext(); urlPath.append(key).append('=').append(urlEncode(value.toString()))) {
            value = var4.next();
            if (isFirst) {
                isFirst = false;
            } else {
                urlPath.append('&');
            }
        }

    }

    private static String joinStreamUrls(StringBuilder urlPath, ArrayList<String> streams) {
        boolean isFirst = true;

        String stream;
        for(Iterator var3 = streams.iterator(); var3.hasNext(); urlPath.append(stream)) {
            stream = (String)var3.next();
            if (isFirst) {
                isFirst = false;
            } else {
                urlPath.append('/');
            }
        }

        return urlPath.toString();
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException(StandardCharsets.UTF_8.name() + " is unsupported", var2);
        }
    }

    private static DecimalFormat getFormatter() {
        if (null == df) {
            df = new DecimalFormat();
            df.setMaximumFractionDigits(30);
            df.setGroupingUsed(false);
        }

        return df;
    }

    public static String buildTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

}
