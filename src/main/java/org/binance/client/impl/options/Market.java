package org.binance.client.impl.options;

import org.binance.client.enums.HttpMethod;
import org.binance.client.utils.ParameterChecker;
import org.binance.client.utils.RequestHandler;

import java.util.LinkedHashMap;

public abstract class Market {

    private String baseUrl;
    private String productUrl;
    private RequestHandler requestHandler;
    private boolean showLimitUsage;
    private final String PING = "/v1/ping";
    private final String TIME = "/v1/time";
    private final String EXCHANGE_INFO = "/v1/exchangeInfo";
    private final String DEPTH = "/v1/depth";
    private final String TRADES = "/v1/trades";
    private final String HISTORICAL_TRADES = "/v1/historicalTrades";
    private final String KLINES = "/v1/klines";
    private final String MARK_PRICE = "/v1/mark";
    private final String TICKER_PRICE_CHANGE_STATISTICS = "/v1/ticker";
    private final String SYMBOL_PRICE_TICKER = "/v1/index";
    private final String HISTORICAL_EXERCISE_RECORDS = "/v1/exerciseHistory";
    private final String OPEN_INTEREST = "/v1/openInterest";

    public Market(String productUrl, String baseUrl, String apiKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.productUrl = productUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.showLimitUsage = showLimitUsage;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public RequestHandler getRequestHandler() {
        return this.requestHandler;
    }

    public boolean getShowLimitUsage() {
        return this.showLimitUsage;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setRequestHandler(String apiKey, String secretKey) {
        new RequestHandler(apiKey, secretKey);
    }

    public void setShowLimitUsage(boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }

    public String ping() {
        return this.requestHandler.sendPublicRequest(this.productUrl, PING, (LinkedHashMap)null, HttpMethod.GET, this.showLimitUsage);
    }

    public String time() {
        return this.requestHandler.sendPublicRequest(this.productUrl, TIME, (LinkedHashMap)null, HttpMethod.GET, this.showLimitUsage);
    }

    public String exchangeInfo() {
        return this.requestHandler.sendPublicRequest(this.productUrl, EXCHANGE_INFO, (LinkedHashMap)null, HttpMethod.GET, this.showLimitUsage);
    }

    public String depth(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendPublicRequest(this.productUrl, DEPTH, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String trades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendPublicRequest(this.productUrl, TRADES, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String historicalTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendWithApiKeyRequest(this.productUrl, HISTORICAL_TRADES, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String klines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "interval", String.class);
        return this.requestHandler.sendPublicRequest(this.productUrl, KLINES, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String markPrice() {
        return this.requestHandler.sendPublicRequest(this.productUrl, MARK_PRICE, (LinkedHashMap)null, HttpMethod.GET, this.showLimitUsage);
    }

    public String ticker24H(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendPublicRequest(this.productUrl, TICKER_PRICE_CHANGE_STATISTICS, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String symbolPriceTicker(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "underlying", String.class);
        return this.requestHandler.sendPublicRequest(this.productUrl, SYMBOL_PRICE_TICKER, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String historicalExerciseRecords(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendPublicRequest(this.productUrl, HISTORICAL_EXERCISE_RECORDS, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String openInterest(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "underlyingAsset", String.class);
        return this.requestHandler.sendPublicRequest(this.productUrl, OPEN_INTEREST, parameters, HttpMethod.GET, this.showLimitUsage);
    }
}
