package com.Booking.BookingApplication.dto.Response;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherResponse {

    private Long teacherId;
    private String name;
    private String email;
    private String timezone;


}
