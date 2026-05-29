package com.Booking.BookingApplication.service;

import com.Booking.BookingApplication.dto.Request.CreateBookingRequest;
import com.Booking.BookingApplication.dto.Response.CreateBookingResponse;

import java.util.List;

public interface BookingService {

    CreateBookingResponse bookOffering(
            CreateBookingRequest request);

    List<CreateBookingResponse> getBookings(
            Long studentId);
}
