package org.binance.client.utils;

import okhttp3.OkHttpClient;

public class HttpClientSingleton {
    private static final OkHttpClient httpClient = new OkHttpClient();

    private HttpClientSingleton() {
    }

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }
}
