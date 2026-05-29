package com.Booking.BookingApplication.repository;

import com.Booking.BookingApplication.entity.Course;
import com.Booking.BookingApplication.entity.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferingRepository extends JpaRepository<Course, Long> {
    List<Offering> findByTeacherId(Long teacherId);
}
