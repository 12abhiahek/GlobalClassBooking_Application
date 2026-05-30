package com.Booking.BookingApplication.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class TimeZoneUtil {

    public Instant convertToUtc(
            String localDateTime,
            String timezone) {

        LocalDateTime ldt =
                LocalDateTime.parse(localDateTime);

        return ldt.atZone(
                        ZoneId.of(timezone))
                .toInstant();
    }

    public LocalDateTime convertUtcToLocal(
            Instant utcTime,
            String timezone) {

        return utcTime.atZone(
                        ZoneId.of(timezone))
                .toLocalDateTime();
    }

    public ZonedDateTime convertUtcToZone(
            Instant utcTime,
            String timezone) {

        return utcTime.atZone(
                ZoneId.of(timezone));
    }
}
