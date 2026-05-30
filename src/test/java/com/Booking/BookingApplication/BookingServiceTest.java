package com.Booking.BookingApplication;

import com.Booking.BookingApplication.dto.Request.CreateBookingRequest;
import com.Booking.BookingApplication.dto.Response.CreateBookingResponse;
import com.Booking.BookingApplication.entity.Booking;
import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.entity.Student;
import com.Booking.BookingApplication.repository.BookingRepository;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.StudentRepository;
import com.Booking.BookingApplication.service.ConflictValidationService;
import com.Booking.BookingApplication.service.impl.BookingServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private OfferingRepository offeringRepository;

    @Mock
    private ConflictValidationService conflictValidationService;

    @InjectMocks
    private BookingServiceImp bookingService;

    @Test
    void shouldBookOfferingSuccessfully() {

        // Student Mock
        Student student = Student.builder()
                .studentId(1L)
                .name("Abhishek")
                .email("abhi@gmail.com")
                .timezone("Asia/Kolkata")
                .build();

        // Offering Mock
        Offering offering = Offering.builder()
                .offeringId(1L)
                .name("Saturday Batch")
                .status("ACTIVE")
                .build();

        // Request Mock
        CreateBookingRequest request =
                new CreateBookingRequest();

        request.setStudentId(1L);
        request.setOfferingId(1L);

        // Saved Booking Mock
        Booking savedBooking = Booking.builder()
                .bookingId(1L)
                .student(student)
                .offering(offering)
                .status("BOOKED")
                .build();

        // Repository Mocks
        when(studentRepository.lockStudent(1L))
                .thenReturn(Optional.of(student));

        when(offeringRepository.findById(1L))
                .thenReturn(Optional.of(offering));

        when(bookingRepository
                .existsByStudentStudentIdAndOfferingOfferingId(
                        1L,
                        1L))
                .thenReturn(false);

        when(bookingRepository.save(any(Booking.class)))
                .thenReturn(savedBooking);

        // Execute
        CreateBookingResponse response =
                bookingService.bookOffering(request);

        // Verify
        assertNotNull(response);

        verify(conflictValidationService)
                .validateBookingConflict(
                        1L,
                        1L);
    }
}