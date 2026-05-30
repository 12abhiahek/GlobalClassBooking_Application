//package com.Booking.BookingApplication;
//
//import com.Booking.BookingApplication.entity.Offering;
//import com.Booking.BookingApplication.exception.BookingConflictException;
//import com.Booking.BookingApplication.repository.BookingRepository;
//import com.Booking.BookingApplication.repository.OfferingRepository;
//import com.Booking.BookingApplication.repository.SessionRepository;
//import com.Booking.BookingApplication.service.impl.ConflictValidationServiceImp;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ConflictValidationServiceTest {
//
//    @Mock
//    private BookingRepository bookingRepository;
//
//    @Mock
//    private OfferingRepository offeringRepository;
//
//    @Mock
//    private SessionRepository sessionRepository;
//
//    @InjectMocks
//    private ConflictValidationServiceImp conflictValidationService;
//
////    @Test
////    void shouldThrowConflictExceptionWhenOverlapExists() {
////
////        Offering offering = Offering.builder()
////                .offeringId(1L)
////                .build();
////
////        when(offeringRepository.findById(1L))
////                .thenReturn(Optional.of(offering));
////
////        assertThrows(
////                BookingConflictException.class,
////                () -> conflictValidationService
////                        .validateBookingConflict(
////                                1L,
////                                1L));
////    }
//
//
//
//}



package com.Booking.BookingApplication;

import com.Booking.BookingApplication.entity.Booking;
import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.entity.Session;
import com.Booking.BookingApplication.exception.BookingConflictException;
import com.Booking.BookingApplication.repository.BookingRepository;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.SessionRepository;
import com.Booking.BookingApplication.service.impl.ConflictValidationServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConflictValidationServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private OfferingRepository offeringRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private ConflictValidationServiceImp conflictValidationService;

    @Test
    void shouldThrowConflictExceptionWhenOverlapExists() {

        // Requested Offering
        Offering requestedOffering = Offering.builder()
                .offeringId(1L)
                .build();

        // Already Booked Offering
        Offering bookedOffering = Offering.builder()
                .offeringId(2L)
                .build();

        // Existing Booking
        Booking booking = Booking.builder()
                .bookingId(1L)
                .offering(bookedOffering)
                .build();

        // Existing Session
        Session bookedSession = Session.builder()
                .sessionId(1L)
                .startTimeUtc(
                        Instant.parse("2026-06-10T17:00:00Z"))
                .endTimeUtc(
                        Instant.parse("2026-06-10T18:00:00Z"))
                .build();

        // Requested Session (overlaps)
        Session requestedSession = Session.builder()
                .sessionId(2L)
                .startTimeUtc(
                        Instant.parse("2026-06-10T17:30:00Z"))
                .endTimeUtc(
                        Instant.parse("2026-06-10T18:30:00Z"))
                .build();

        when(offeringRepository.findById(1L))
                .thenReturn(Optional.of(requestedOffering));

        when(sessionRepository.findByOfferingOfferingId(1L))
                .thenReturn(List.of(requestedSession));

        when(bookingRepository.findByStudentStudentId(1L))
                .thenReturn(List.of(booking));

        when(sessionRepository.findByOfferingOfferingId(2L))
                .thenReturn(List.of(bookedSession));

        assertThrows(
                BookingConflictException.class,
                () -> conflictValidationService
                        .validateBookingConflict(
                                1L,
                                1L));
    }
}