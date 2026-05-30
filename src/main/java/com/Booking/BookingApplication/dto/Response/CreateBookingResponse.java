package com.Booking.BookingApplication.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingResponse {

    private Long bookingId;

    private String studentName;

    private String offeringName;

    private String status;
}
