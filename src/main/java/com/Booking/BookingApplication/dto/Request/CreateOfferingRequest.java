package com.Booking.BookingApplication.dto.Request;

import lombok.Data;

@Data
public class CreateOfferingRequest {
    private Long teacherId;
    private Long courseId;
    private String name;
}
