package com.Booking.BookingApplication.mapper;

import com.Booking.BookingApplication.dto.Response.CreateStudentResponse;
import com.Booking.BookingApplication.entity.Student;

public class StudentMapper {

    public static CreateStudentResponse toResponse(
            Student student) {

        return CreateStudentResponse.builder()
                .studentId(student.getStudentId())
                .name(student.getName())
                .email(student.getEmail())
                .timezone(student.getTimezone())
                .build();
    }
}
