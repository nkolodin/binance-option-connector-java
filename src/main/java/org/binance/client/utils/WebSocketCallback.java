package org.binance.client.utils;

@FunctionalInterface
public interface WebSocketCallback {
    void onReceive(String var1);
}
