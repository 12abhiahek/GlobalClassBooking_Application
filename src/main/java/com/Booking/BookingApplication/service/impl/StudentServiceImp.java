package com.Booking.BookingApplication.service.impl;

import com.Booking.BookingApplication.dto.Request.CreateStudentRequest;
import com.Booking.BookingApplication.dto.Response.CreateStudentResponse;
import com.Booking.BookingApplication.entity.Student;
import com.Booking.BookingApplication.exception.ResourceNotFoundException;
import com.Booking.BookingApplication.mapper.StudentMapper;
import com.Booking.BookingApplication.repository.StudentRepository;
import com.Booking.BookingApplication.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public CreateStudentResponse createStudent(
            CreateStudentRequest request) {

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .timezone(request.getTimezone())
                .build();

        return StudentMapper.toResponse(
                studentRepository.save(student));
    }

    @Override
    public CreateStudentResponse getStudent(
            Long studentId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"));

        return StudentMapper.toResponse(student);
    }

    @Override
    public List<CreateStudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

}
