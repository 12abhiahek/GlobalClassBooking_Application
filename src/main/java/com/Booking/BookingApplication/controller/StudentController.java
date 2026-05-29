package com.Booking.BookingApplication.controller;


import com.Booking.BookingApplication.dto.Request.CreateStudentRequest;
import com.Booking.BookingApplication.dto.Response.CreateOfferingResponse;
import com.Booking.BookingApplication.dto.Response.CreateSessionResponse;
import com.Booking.BookingApplication.dto.Response.CreateStudentResponse;
import com.Booking.BookingApplication.entity.Student;
import com.Booking.BookingApplication.exception.ResourceNotFoundException;
import com.Booking.BookingApplication.repository.StudentRepository;
import com.Booking.BookingApplication.service.OfferingService;
import com.Booking.BookingApplication.service.SessionService;
import com.Booking.BookingApplication.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final OfferingService offeringService;
    private final SessionService sessionService;
    private final StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<CreateStudentResponse> createStudent(
            @RequestBody CreateStudentRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        studentService.createStudent(
                                request));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<CreateStudentResponse> getStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                studentService.getStudent(
                        studentId));
    }

    @GetMapping("/offerings")
    public ResponseEntity<List<CreateOfferingResponse>>
    getAvailableOfferings() {

        return ResponseEntity.ok(
                offeringService.getAvailableOfferings());
    }

    @GetMapping(
            "/offerings/{offeringId}/sessions")
    public ResponseEntity<List<CreateSessionResponse>>
    getOfferingSessionsForStudent(
            @PathVariable Long offeringId,
            @RequestParam Long studentId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"));

        return ResponseEntity.ok(
                sessionService.getStudentSessions(
                        offeringId,
                        student.getTimezone()));
    }
}
