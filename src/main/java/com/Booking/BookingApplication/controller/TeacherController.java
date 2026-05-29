package com.Booking.BookingApplication.controller;


import com.Booking.BookingApplication.dto.Request.CreateCourseRequest;
import com.Booking.BookingApplication.dto.Request.CreateOfferingRequest;
import com.Booking.BookingApplication.dto.Request.CreateSessionRequest;
import com.Booking.BookingApplication.dto.Request.CreateTeacherRequest;
import com.Booking.BookingApplication.dto.Response.CreateOfferingResponse;
import com.Booking.BookingApplication.dto.Response.CreateSessionResponse;
import com.Booking.BookingApplication.dto.Response.CreateTeacherResponse;
import com.Booking.BookingApplication.entity.Course;
import com.Booking.BookingApplication.repository.CourseRepository;
import com.Booking.BookingApplication.service.OfferingService;
import com.Booking.BookingApplication.service.SessionService;
import com.Booking.BookingApplication.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final OfferingService offeringService;
    private final SessionService sessionService;
    private final CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<CreateTeacherResponse> createTeacher(
            @RequestBody CreateTeacherRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teacherService.createTeacher(request));
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<CreateTeacherResponse> getTeacher(
            @PathVariable Long teacherId) {

        return ResponseEntity.ok(
                teacherService.getTeacher(teacherId));
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(
            @RequestBody CreateCourseRequest request) {

        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseRepository.save(course));
    }

    @PostMapping("/offerings")
    public ResponseEntity<CreateOfferingResponse> createOffering(
            @RequestBody CreateOfferingRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(offeringService.createOffering(request));
    }

    @GetMapping("/{teacherId}/offerings")
    public ResponseEntity<?> getTeacherOfferings(
            @PathVariable Long teacherId) {

        return ResponseEntity.ok(
                offeringService.getTeacherOfferings(
                        teacherId));
    }

    @PostMapping("/offerings/{offeringId}/sessions")
    public ResponseEntity<CreateSessionResponse> addSession(
            @PathVariable Long offeringId,
            @RequestBody CreateSessionRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        sessionService.addSession(
                                offeringId,
                                request));
    }

    @GetMapping("/offerings/{offeringId}/sessions")
    public ResponseEntity<?> getOfferingSessions(
            @PathVariable Long offeringId) {

        return ResponseEntity.ok(
                sessionService.getOfferingSessions(
                        offeringId));
    }
}
