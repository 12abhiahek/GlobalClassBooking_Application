package com.Booking.BookingApplication.dto.Request;

import lombok.Data;

@Data
public class CreateStudentRequest {
    private String name;
    private String email;
    private String timezone;
}
