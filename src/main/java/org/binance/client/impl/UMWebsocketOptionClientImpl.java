package org.binance.client.impl;

public class UMWebsocketOptionClientImpl extends WebsocketClientImpl{
    public UMWebsocketOptionClientImpl() {
        super("wss://nbstream.binance.com/eoptions");
    }

    public UMWebsocketOptionClientImpl(String baseUrl) {
        super(baseUrl);
    }

}
