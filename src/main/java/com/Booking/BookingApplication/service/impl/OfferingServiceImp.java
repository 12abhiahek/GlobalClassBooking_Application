package com.Booking.BookingApplication.service.impl;

import com.Booking.BookingApplication.dto.Request.CreateOfferingRequest;
import com.Booking.BookingApplication.dto.Response.CreateOfferingResponse;
import com.Booking.BookingApplication.entity.Course;
import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.entity.Teacher;
import com.Booking.BookingApplication.exception.ResourceNotFoundException;
import com.Booking.BookingApplication.mapper.OfferingMapper;
import com.Booking.BookingApplication.repository.CourseRepository;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.TeacherRepository;
import com.Booking.BookingApplication.service.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferingServiceImp implements OfferingService {

    private final OfferingRepository offeringRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Override
    public CreateOfferingResponse createOffering(
            CreateOfferingRequest request) {

        Teacher teacher =
                teacherRepository.findById(
                                request.getTeacherId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Teacher not found"));

        Course course =
                courseRepository.findById(
                                request.getCourseId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course not found"));

        Offering offering =
                Offering.builder()
                        .teacher(teacher)
                        .course(course)
                        .name(request.getName())
                        .status("ACTIVE")
                        .build();

        return OfferingMapper.toResponse(
                offeringRepository.save(offering));
    }

    @Override
    public List<CreateOfferingResponse> getTeacherOfferings(
            Long teacherId) {

        return offeringRepository
                .findByTeacherTeacherId(teacherId)
                .stream()
                .map(OfferingMapper::toResponse)
                .toList();
    }

    @Override
    public List<CreateOfferingResponse> getAvailableOfferings() {

        return offeringRepository.findAll()
                .stream()
                .map(OfferingMapper::toResponse)
                .toList();
    }
}
