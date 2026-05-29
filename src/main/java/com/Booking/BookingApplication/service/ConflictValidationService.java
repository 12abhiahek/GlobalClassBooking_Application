package com.Booking.BookingApplication.service;

public interface ConflictValidationService {

    void validateBookingConflict(
            Long studentId,
            Long offeringId
    );
}
