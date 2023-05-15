package org.binance.logging.util;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MsEpochConverter extends ClassicConverter {
    public MsEpochConverter() {
    }

    public String convert(ILoggingEvent e) {
        return Long.toString(e.getTimeStamp());
    }

}
