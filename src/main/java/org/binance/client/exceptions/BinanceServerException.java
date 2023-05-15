package org.binance.client.exceptions;

public class BinanceServerException  extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final int invalidStatusCode = -1;
    private final int httpStatusCode;

    public BinanceServerException(String fullErrMsg) {
        super(fullErrMsg);
        this.httpStatusCode = -1;
    }

    public BinanceServerException(String fullErrMsg, int httpStatusCode) {
        super(fullErrMsg);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
