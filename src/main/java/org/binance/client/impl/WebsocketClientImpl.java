package org.binance.client.impl;

import okhttp3.Request;
import org.binance.client.WebsocketClient;
import org.binance.client.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebsocketClientImpl implements WebsocketClient {

    private final String baseUrl;
    private final Map<Integer, WebSocketConnection> connections = new HashMap();
    private final WebSocketCallback noopCallback = (msg) -> {
    };
    private static final Logger logger = LoggerFactory.getLogger(WebsocketClientImpl.class);

    public WebsocketClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public WebSocketCallback getNoopCallback() {
        return this.noopCallback;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public int aggTradeStream(String symbol, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.aggTradeStream(symbol, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int aggTradeStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@aggTrade", this.baseUrl, symbol.toLowerCase()));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int markPriceStream(String symbol, int speed, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.markPriceStream(symbol, speed, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int markPriceStream(String symbol, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@markPrice@%ss", this.baseUrl, symbol.toLowerCase(), speed));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int klineStream(String symbol, String interval, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.klineStream(symbol, interval, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int klineStream(String symbol, String interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@kline_%s", this.baseUrl, symbol, interval));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int continuousKlineStream(String pair, String contractType, String interval, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(pair, String.class, "pair");
        return this.continuousKlineStream(pair, contractType, interval, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int continuousKlineStream(String pair, String contractType, String interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(pair, String.class, "pair");
        ParameterChecker.checkParameterType(contractType, String.class, "contractType");
        ParameterChecker.checkParameterType(interval, String.class, "interval");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s_%s@continuousKline_%s", this.baseUrl, pair.toLowerCase(), contractType, interval));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int miniTickerStream(String symbol, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.miniTickerStream(symbol, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int miniTickerStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@miniTicker", this.baseUrl, symbol.toLowerCase()));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int allMiniTickerStream(WebSocketCallback onMessageCallback) {
        return this.allMiniTickerStream(this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int allMiniTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!miniTicker@arr", this.baseUrl));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int symbolTicker(String symbol, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.symbolTicker(symbol, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int symbolTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@ticker", this.baseUrl, symbol.toLowerCase()));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int allTickerStream(WebSocketCallback onMessageCallback) {
        return this.allTickerStream(this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int allTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!ticker@arr", this.baseUrl));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int bookTicker(String symbol, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.bookTicker(symbol, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int bookTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@bookTicker", this.baseUrl, symbol.toLowerCase()));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int allBookTickerStream(WebSocketCallback onMessageCallback) {
        return this.allBookTickerStream(this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int allBookTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!bookTicker", this.baseUrl));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }


    public int forceOrderStream(String symbol, WebSocketCallback onMessageCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return this.forceOrderStream(symbol, this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int forceOrderStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@forceOrder", this.baseUrl, symbol.toLowerCase()));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int allForceOrderStream(WebSocketCallback onMessageCallback) {
        return this.allForceOrderStream(this.noopCallback, onMessageCallback, this.noopCallback, this.noopCallback);
    }

    public int allForceOrderStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!forceOrder@arr", this.baseUrl));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int listenUserStream(String listenKey, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s", this.baseUrl, listenKey));
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public int combineStreams(ArrayList<String> streams, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        String url = UrlBuilder.buildStreamUrl(String.format("%s/stream", this.baseUrl), streams);
        Request request = RequestBuilder.buildWebsocketRequest(url);
        return this.createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    public void closeConnection(int connectionId) {
        if (this.connections.containsKey(connectionId)) {
            ((WebSocketConnection)this.connections.get(connectionId)).close();
            logger.info("Closing Connection ID {}", connectionId);
            this.connections.remove(connectionId);
        } else {
            logger.info("Connection ID {} does not exist!", connectionId);
        }

    }

    public void closeAllConnections() {
        if (!this.connections.isEmpty()) {
            logger.info("Closing {} connections(s)", this.connections.size());
            Iterator<Map.Entry<Integer, WebSocketConnection>> iter = this.connections.entrySet().iterator();

            while(iter.hasNext()) {
                WebSocketConnection connection = (WebSocketConnection)((Map.Entry)iter.next()).getValue();
                connection.close();
                iter.remove();
            }
        }

        if (this.connections.isEmpty()) {
            HttpClientSingleton.getHttpClient().dispatcher().executorService().shutdown();
            logger.info("All connections are closed!");
        }

    }

    public int createConnection(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback, Request request) {
        WebSocketConnection connection = new WebSocketConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
        connection.connect();
        int connectionId = connection.getConnectionId();
        this.connections.put(connectionId, connection);
        return connectionId;
    }
}
