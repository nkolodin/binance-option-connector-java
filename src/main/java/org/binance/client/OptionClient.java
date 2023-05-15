package org.binance.client;


import org.binance.client.impl.options.Account;
import org.binance.client.impl.options.Market;

public interface OptionClient {

    Market market();

    Account account();
}
