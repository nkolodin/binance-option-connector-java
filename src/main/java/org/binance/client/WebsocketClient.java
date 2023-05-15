package org.binance.client;

import org.binance.client.utils.WebSocketCallback;

public interface WebsocketClient {

    int symbolTicker(String var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5);

    int klineStream(String var1, String var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5, WebSocketCallback var6);

    int aggTradeStream(String var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5);


    int miniTickerStream(String var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5);


    int allTickerStream(WebSocketCallback var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4);


    int allMiniTickerStream(WebSocketCallback var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4);


    int bookTicker(String var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5);


    int allBookTickerStream(WebSocketCallback var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4);


    int markPriceStream(String var1, int var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5, WebSocketCallback var6);


    int continuousKlineStream(String var1, String var2, String var3, WebSocketCallback var4, WebSocketCallback var5, WebSocketCallback var6, WebSocketCallback var7);


    int forceOrderStream(String var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4, WebSocketCallback var5);


    int allForceOrderStream(WebSocketCallback var1, WebSocketCallback var2, WebSocketCallback var3, WebSocketCallback var4);
}
