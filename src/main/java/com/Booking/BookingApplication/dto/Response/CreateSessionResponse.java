package com.Booking.BookingApplication.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionResponse {

    private Long sessionId;
    private Long offeringId;
    private String startTime;
    private String endTime;

}
