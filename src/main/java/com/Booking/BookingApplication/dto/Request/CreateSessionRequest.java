package com.Booking.BookingApplication.dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSessionRequest {


    private String startTime;

    private String endTime;

    private String timezone;
}
