package com.Booking.BookingApplication;

import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.exception.BookingConflictException;
import com.Booking.BookingApplication.repository.BookingRepository;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConflictValidationServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private OfferingRepository offeringRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private ConflictValidationServiceImpl conflictValidationService;

    @Test
    void shouldThrowConflictExceptionWhenOverlapExists() {

        Offering offering = Offering.builder()
                .id(1L)
                .build();

        when(offeringRepository.findById(1L))
                .thenReturn(Optional.of(offering));

        assertThrows(
                BookingConflictException.class,
                () -> conflictValidationService
                        .validateBookingConflict(
                                1L,
                                1L));
    }
}
