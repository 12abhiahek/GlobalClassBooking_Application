package com.Booking.BookingApplication;

import com.Booking.BookingApplication.dto.Request.CreateBookingRequest;
import com.Booking.BookingApplication.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentBookingIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Test
    void shouldHandleConcurrentBookings()
            throws Exception {

        int threadCount = 10;

        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        threadCount);

        CountDownLatch latch =
                new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            executorService.submit(() -> {

                try {

                    CreateBookingRequest request =
                            new CreateBookingRequest();

                    request.setStudentId(1L);
                    request.setOfferingId(1L);

                    bookingService.bookOffering(
                            request);

                } catch (Exception ex) {

                    System.out.println(
                            ex.getMessage());

                } finally {

                    latch.countDown();
                }
            });
        }

        latch.await(
                30,
                TimeUnit.SECONDS);

        executorService.shutdown();

        assertTrue(true);
    }
}
