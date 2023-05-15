package org.binance.client.impl;

import org.binance.client.OptionClient;

public abstract class OptionClientImpl implements OptionClient {

    private final String apiKey;
    private final String secretKey;
    private final String baseUrl;
    private final String productUrl;
    private boolean showLimitUsage;

    public OptionClientImpl(String baseUrl, String product) {
        this((String)null, (String)null, baseUrl, product);
    }

    public OptionClientImpl(String baseUrl, String product, boolean showLimitUsage) {
        this((String)null, (String)null, baseUrl, product, showLimitUsage);
    }

    public OptionClientImpl(String apiKey, String secretKey, String baseUrl, String product) {
        this(apiKey, secretKey, baseUrl, product, false);
    }

    public OptionClientImpl(String apiKey, String secretKey, String baseUrl, String product, boolean showLimitUsage) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
        this.productUrl = baseUrl + product;
        this.showLimitUsage = showLimitUsage;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public boolean getShowLimitUsage() {
        return showLimitUsage;
    }

    public void setShowLimitUsage(boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }
}
