package com.Booking.BookingApplication.service;

import com.Booking.BookingApplication.dto.Request.CreateTeacherRequest;
import com.Booking.BookingApplication.dto.Response.CreateTeacherResponse;

import java.util.List;

public interface TeacherService {
    CreateTeacherResponse createTeacher(
            CreateTeacherRequest request);

    CreateTeacherResponse getTeacher(
            Long teacherId);

    List<CreateTeacherResponse> getAllTeachers();
}
