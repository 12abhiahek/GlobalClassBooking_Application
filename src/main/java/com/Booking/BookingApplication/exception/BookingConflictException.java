package com.Booking.BookingApplication.exception;

public class BookingConflictException extends RuntimeException{
    public BookingConflictException(
            String message) {

        super(message);
    }
}
