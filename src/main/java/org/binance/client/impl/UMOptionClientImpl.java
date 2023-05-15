package org.binance.client.impl;

import org.binance.client.impl.options.OPAccount;
import org.binance.client.impl.options.OPMarket;

public class UMOptionClientImpl extends OptionClientImpl{

    private static String defaultBaseUrl = "https://eapi.binance.com";
    private static String umProduct = "/eapi";

    public UMOptionClientImpl() {
        super(defaultBaseUrl, umProduct);
    }

    public UMOptionClientImpl(String baseUrl) {
        super(baseUrl, umProduct);
    }

    public UMOptionClientImpl(String apiKey, String secretKey) {
        super(apiKey, secretKey, defaultBaseUrl, umProduct);
    }

    public UMOptionClientImpl(String baseUrl, boolean showLimitUsage) {
        super(baseUrl, umProduct, showLimitUsage);
    }

    public UMOptionClientImpl(String apiKey, String secretKey, boolean showLimitUsage) {
        super(apiKey, secretKey, defaultBaseUrl, umProduct, showLimitUsage);
    }

    public UMOptionClientImpl(String apiKey, String secretKey, String baseUrl) {
        super(apiKey, secretKey, baseUrl, umProduct);
    }

    public OPMarket market() {
        return new OPMarket(this.getProductUrl(), this.getBaseUrl(), this.getApiKey(), this.getShowLimitUsage());
    }

    public OPAccount account() {
        return new OPAccount(this.getProductUrl(), this.getApiKey(), this.getSecretKey(), this.getShowLimitUsage());
    }
}
