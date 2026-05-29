package com.Booking.BookingApplication.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static final DateTimeFormatter API_FORMATTER =
            DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss"
            );

    public static String format(
            LocalDateTime localDateTime) {

        return localDateTime.format(
                API_FORMATTER
        );
    }
}
