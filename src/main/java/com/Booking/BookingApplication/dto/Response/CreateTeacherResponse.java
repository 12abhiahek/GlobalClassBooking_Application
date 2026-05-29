package com.Booking.BookingApplication.dto.Response;

import jdk.jshell.Snippet;
import lombok.Data;

@Data
public class CreateTeacherResponse {

    private Long teacherId;
    private String name;
    private String email;
    private String timezone;


}
