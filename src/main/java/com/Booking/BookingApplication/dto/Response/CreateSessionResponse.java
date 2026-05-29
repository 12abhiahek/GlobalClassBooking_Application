package com.Booking.BookingApplication.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSessionResponse {

    private Long sessionId;
    private Long bookingId;
    private String startTime;
    private String endTime;

}
