package com.Booking.BookingApplication.controller;

import com.Booking.BookingApplication.dto.Request.CreateBookingRequest;
import com.Booking.BookingApplication.dto.Response.CreateBookingResponse;
import com.Booking.BookingApplication.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<CreateBookingResponse>
    bookOffering(
            @RequestBody CreateBookingRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        bookingService.bookOffering(
                                request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getBookings(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                bookingService.getBookings(
                        studentId));
    }
}
