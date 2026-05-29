package com.Booking.BookingApplication.dto.Response;

import lombok.Data;

@Data
public class CreateOfferingResponse {

    private Long offeringId;
    private String name;
    private String status;

    private String teacherName;

    private String courseName;
}
