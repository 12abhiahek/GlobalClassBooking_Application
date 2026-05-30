package com.Booking.BookingApplication.service;

import com.Booking.BookingApplication.dto.Request.CreateOfferingRequest;
import com.Booking.BookingApplication.dto.Response.CreateOfferingResponse;

import java.util.List;

public interface OfferingService {

    CreateOfferingResponse createOffering(
            CreateOfferingRequest request);

    List<CreateOfferingResponse> getTeacherOfferings(
            Long teacherId);

    List<CreateOfferingResponse> getAvailableOfferings();

}
