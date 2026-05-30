package com.Booking.BookingApplication.mapper;

import com.Booking.BookingApplication.dto.Response.CreateOfferingResponse;
import com.Booking.BookingApplication.entity.Offering;

public class OfferingMapper {

    public static CreateOfferingResponse toResponse(
            Offering offering) {

        return CreateOfferingResponse.builder()
                .offeringId(offering.getOfferingId())
                .name(offering.getName())
                .status(offering.getStatus())
                .teacherName(
                        offering.getTeacher().getName())
                .courseName(
                        offering.getCourse().getName())
                .build();
    }
}
