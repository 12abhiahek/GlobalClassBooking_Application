package com.Booking.BookingApplication.service;

import com.Booking.BookingApplication.dto.Request.CreateStudentRequest;
import com.Booking.BookingApplication.dto.Response.CreateStudentResponse;

import java.util.List;

public interface StudentService {
    CreateStudentResponse createStudent(
            CreateStudentRequest request);

    CreateStudentResponse getStudent(
            Long studentId);

    List<CreateStudentResponse> getAllStudents();
}
