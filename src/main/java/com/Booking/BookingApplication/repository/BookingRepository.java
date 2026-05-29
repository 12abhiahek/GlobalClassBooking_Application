package com.Booking.BookingApplication.repository;

import com.Booking.BookingApplication.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
        // Custom query methods can be defined here if needed
        List<Booking> findByStudentId(Long studentId);

    boolean existsByStudentIdAndOfferingId(
            Long studentId,
            Long offeringId
    );

}
