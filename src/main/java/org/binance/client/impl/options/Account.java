package org.binance.client.impl.options;

import org.binance.client.enums.HttpMethod;
import org.binance.client.utils.ParameterChecker;
import org.binance.client.utils.RequestHandler;

import java.util.LinkedHashMap;

public abstract class Account {

    private String productUrl;
    private RequestHandler requestHandler;
    private boolean showLimitUsage;
    private final String OPEN_ORDERS = "/v1/openOrders";
    private final String USER_TRADES = "/v1/userTrades";
    private final String ORDER = "/v1/order";
    private final String BATCH_ORDERS = "/v1/batchOrders";
    private final String ALL_OPEN_ORDERS = "/v1/allOpenOrders";
    private final String CANCEL_ALL = "/v1/countdownCancelAll";
    private final String ACCOUNT_INFORMATION = "/v1/account";
    private final String ALL_OPTION_ORDERS_BY_UNDERLYING = "/v1/allOpenOrdersByUnderlying";
    private final String HISTORY_ORDERS = "/v1/historyOrders";
    private final String OPTION_POSITION_INFORMATION = "/v1/position";
    private final String EXERCISE_RECORD = "/v1/exerciseRecord";
    private final String ACCOUNT_FOUNDING_FLOW = "/v1/bill";

    public Account(String productUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.productUrl = productUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
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

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setRequestHandler(String apiKey, String secretKey) {
        this.requestHandler = new RequestHandler(apiKey, secretKey);
    }

    public void setShowLimitUsage(boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }

    public String currentAllOpenOrders(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendSignedRequest(this.productUrl, OPEN_ORDERS, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String accountTradeList(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendSignedRequest(this.productUrl, USER_TRADES, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String newOrder(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        ParameterChecker.checkParameter(parameters, "quantity", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, ORDER, parameters, HttpMethod.POST, this.showLimitUsage);
    }

    public String checkOrderStatus(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, ORDER, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String cancelOptionOrder(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, ORDER, parameters, HttpMethod.DELETE, this.showLimitUsage);
    }

    public String placeMultipleOrders(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkRequiredParameter(parameters, "orders");
        return this.requestHandler.sendSignedRequest(this.productUrl, BATCH_ORDERS, parameters, HttpMethod.POST, this.showLimitUsage);
    }

    public String cancelAnActiveOrder(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkRequiredParameter(parameters, "symbol");
        return this.requestHandler.sendSignedRequest(this.productUrl, BATCH_ORDERS, parameters, HttpMethod.DELETE, this.showLimitUsage);
    }

    public String cancelAllOpenOrders(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, ALL_OPEN_ORDERS, parameters, HttpMethod.DELETE, this.showLimitUsage);
    }

    public String autoCancelOpen(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "countdownTime", Integer.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, CANCEL_ALL, parameters, HttpMethod.POST, this.showLimitUsage);
    }

    //Considering the possible data latency from RESTful endpoints during an extremely volatile market,
    // it is highly recommended to get the order status, position, etc from the Websocket user data stream.
    public String accountInformation(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendSignedRequest(this.productUrl, ACCOUNT_INFORMATION, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String cancelAllOptionOrdersByUnderlying(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "underlying", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, ALL_OPTION_ORDERS_BY_UNDERLYING, parameters, HttpMethod.DELETE, this.showLimitUsage);
    }

    public String optionOrderHistory(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, HISTORY_ORDERS, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String optionPositionInformation(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendSignedRequest(this.productUrl, OPTION_POSITION_INFORMATION, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String userExerciseRecord(LinkedHashMap<String, Object> parameters) {
        return this.requestHandler.sendSignedRequest(this.productUrl, EXERCISE_RECORD, parameters, HttpMethod.GET, this.showLimitUsage);
    }

    public String accountFoundingFlow(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "currency", String.class);
        return this.requestHandler.sendSignedRequest(this.productUrl, ACCOUNT_FOUNDING_FLOW, parameters, HttpMethod.GET, this.showLimitUsage);
    }

}
