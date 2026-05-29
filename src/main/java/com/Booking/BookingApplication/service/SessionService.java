package com.Booking.BookingApplication.service;

import com.Booking.BookingApplication.dto.Request.CreateSessionRequest;
import com.Booking.BookingApplication.dto.Response.CreateSessionResponse;

import java.util.List;

public interface SessionService {

    CreateSessionResponse addSession(
            Long offeringId,
            CreateSessionRequest request);

    List<CreateSessionResponse> getOfferingSessions(
            Long offeringId);

    List<CreateSessionResponse> getStudentSessions(
            Long offeringId,
            String timezone);
}
