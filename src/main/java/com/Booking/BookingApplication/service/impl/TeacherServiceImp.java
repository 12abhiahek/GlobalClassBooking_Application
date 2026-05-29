package com.Booking.BookingApplication.service.impl;

import com.Booking.BookingApplication.dto.Request.CreateTeacherRequest;
import com.Booking.BookingApplication.dto.Response.CreateTeacherResponse;
import com.Booking.BookingApplication.entity.Teacher;
import com.Booking.BookingApplication.exception.ResourceNotFoundException;
import com.Booking.BookingApplication.mapper.TeacherMapper;
import com.Booking.BookingApplication.repository.TeacherRepository;
import com.Booking.BookingApplication.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public CreateTeacherResponse createTeacher(
            CreateTeacherRequest request) {

        Teacher teacher = Teacher.builder()
                .name(request.getName())
                .email(request.getEmail())
                .timezone(request.getTimezone())
                .build();

        return TeacherMapper.toResponse(
                teacherRepository.save(teacher));
    }

    @Override
    public CreateTeacherResponse getTeacher(
            Long teacherId) {

        Teacher teacher =
                teacherRepository.findById(teacherId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Teacher not found"));

        return TeacherMapper.toResponse(teacher);
    }

    @Override
    public List<CreateTeacherResponse> getAllTeachers() {

        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::toResponse)
                .toList();
    }
}
