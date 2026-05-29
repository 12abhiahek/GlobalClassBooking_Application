package com.Booking.BookingApplication.mapper;

import com.Booking.BookingApplication.dto.Response.CreateTeacherResponse;
import com.Booking.BookingApplication.entity.Teacher;

public class TeacherMapper {

    public static CreateTeacherResponse toResponse(
            Teacher teacher) {

        return CreateTeacherResponse.builder()
                .id(teacher.getTeacherId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .timezone(teacher.getTimezone())
                .build();
    }
}
