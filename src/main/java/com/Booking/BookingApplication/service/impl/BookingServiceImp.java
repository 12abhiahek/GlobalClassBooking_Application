package com.Booking.BookingApplication.service.impl;

import com.Booking.BookingApplication.dto.Request.CreateBookingRequest;
import com.Booking.BookingApplication.dto.Response.CreateBookingResponse;
import com.Booking.BookingApplication.entity.Booking;
import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.entity.Student;
import com.Booking.BookingApplication.repository.BookingRepository;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.StudentRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;

    private final StudentRepository studentRepository;

    private final OfferingRepository offeringRepository;

    private final ConflictValidationService
            conflictValidationService;

    @Override
    @Transactional
    public CreateBookingResponse bookOffering(
            CreateBookingRequest request) {

        Student student =
                studentRepository.lockStudent(
                                request.getStudentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"));

        Offering offering =
                offeringRepository.findById(
                                request.getOfferingId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Offering not found"));

        boolean alreadyBooked =
                bookingRepository.existsByStudentIdAndOfferingId(
                        student.getStudentId(),
                        offering.getId());

        if (alreadyBooked) {

            throw new RuntimeException(
                    "Offering already booked");
        }

        conflictValidationService
                .validateBookingConflict(
                        student.getStudentId(),
                        offering.getId());

        Booking booking =
                Booking.builder()
                        .student(student)
                        .offering(offering)
                        .status("BOOKED")
                        .build();

        Booking savedBooking =
                bookingRepository.save(
                        booking);

        return BookingMapper.toResponse(
                savedBooking);
    }

    @Override
    public List<CreateBookingResponse> getBookings(
            Long studentId) {

        List<Booking> bookings =
                bookingRepository.findByStudentId(
                        studentId);

        return bookings.stream()
                .map(BookingMapper::toResponse)
                .toList();
    }
}
