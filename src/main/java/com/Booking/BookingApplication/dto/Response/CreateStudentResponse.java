package com.Booking.BookingApplication.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentResponse {
    private Long studentId;
    private String name;
    private String email;
    private String timezone;
}
