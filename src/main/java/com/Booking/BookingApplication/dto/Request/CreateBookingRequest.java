package com.Booking.BookingApplication.dto.Request;

import lombok.Data;

@Data
public class CreateBookingRequest {
    private Long studentId;
    private Long offeringId;

}
