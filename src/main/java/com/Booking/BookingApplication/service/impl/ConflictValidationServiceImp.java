package com.Booking.BookingApplication.service.impl;

import com.Booking.BookingApplication.entity.Booking;
import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.entity.Session;
import com.Booking.BookingApplication.exception.BookingConflictException;
import com.Booking.BookingApplication.exception.ResourceNotFoundException;
import com.Booking.BookingApplication.repository.BookingRepository;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.SessionRepository;
import com.Booking.BookingApplication.service.ConflictValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConflictValidationServiceImp implements ConflictValidationService {

    private final BookingRepository bookingRepository;
    private final OfferingRepository offeringRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void validateBookingConflict(
            Long studentId,
            Long offeringId) {

        Offering requestedOffering =
                offeringRepository.findById(offeringId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Offering not found"));

        List<Session> requestedSessions =
                sessionRepository.findByOfferingOfferingId(
                        requestedOffering.getOfferingId());

        List<Booking> existingBookings =
                bookingRepository.findByStudentStudentId(
                        studentId);

        for (Booking booking : existingBookings) {

            List<Session> bookedSessions =
                    sessionRepository.findByOfferingOfferingId(
                            booking.getOffering().getOfferingId());

            for (Session bookedSession : bookedSessions) {

                for (Session requestedSession : requestedSessions) {

                    if (isOverlap(
                            bookedSession.getStartTimeUtc(),
                            bookedSession.getEndTimeUtc(),
                            requestedSession.getStartTimeUtc(),
                            requestedSession.getEndTimeUtc()
                    )) {

                        throw new BookingConflictException(
                                "Booking conflict detected with existing booked session");
                    }
                }
            }
        }
    }

    private boolean isOverlap(
            Instant start1,
            Instant end1,
            Instant start2,
            Instant end2) {

        return start1.isBefore(end2)
                &&
                start2.isBefore(end1);
    }
}
