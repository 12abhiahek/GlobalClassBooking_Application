package com.Booking.BookingApplication.dto.Response;

import lombok.Data;

@Data
public class CreateStudentResponse {
    private Long studentId;
    private String name;
    private String email;
    private String timezone;
}
