package org.binance.client;

import org.binance.client.utils.WebSocketCallback;

public interface WebsocketClient {

    int symbolTicker(String var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5);

    int klineStream(String var1, String var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5, WebSocketCallback var6);

}
