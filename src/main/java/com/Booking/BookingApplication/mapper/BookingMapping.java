package com.Booking.BookingApplication.mapper;

import com.Booking.BookingApplication.dto.Response.CreateBookingResponse;
import com.Booking.BookingApplication.entity.Booking;

public class BookingMapping {

    public static CreateBookingResponse toResponse(
            Booking booking) {

        return CreateBookingResponse.builder()
                .bookingId(booking.getBookingId())
                .studentName(
                        booking.getStudent().getName())
                .offeringName(
                        booking.getOffering().getName())
                .status(booking.getStatus())
                .build();
    }
}
