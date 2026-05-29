package com.Booking.BookingApplication.dto.Response;

import lombok.Data;

@Data
public class CreateBookingResponse {

    private Long bookingId;

    private String studentName;

    private String offeringName;

    private String status;
}
