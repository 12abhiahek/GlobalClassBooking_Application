package com.Booking.BookingApplication.service.impl;

import com.Booking.BookingApplication.dto.Request.CreateSessionRequest;
import com.Booking.BookingApplication.dto.Response.CreateSessionResponse;
import com.Booking.BookingApplication.entity.Offering;
import com.Booking.BookingApplication.entity.Session;
import com.Booking.BookingApplication.exception.ResourceNotFoundException;
import com.Booking.BookingApplication.repository.OfferingRepository;
import com.Booking.BookingApplication.repository.SessionRepository;
import com.Booking.BookingApplication.utils.TimeZoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImp implements com.Booking.BookingApplication.service.SessionService {

    private final SessionRepository sessionRepository;
    private final OfferingRepository offeringRepository;
    private final TimeZoneUtil timeZoneUtil;

    @Override
    public CreateSessionResponse addSession(
            Long offeringId,
            CreateSessionRequest request) {

        Offering offering =
                offeringRepository.findById(
                                offeringId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Offering not found"));

        Instant startUtc =
                timeZoneUtil.convertToUtc(
                        request.getStartTime(),
                        request.getTimezone());

        Instant endUtc =
                timeZoneUtil.convertToUtc(
                        request.getEndTime(),
                        request.getTimezone());

        Session session =
                Session.builder()
                        .offering(offering)
                        .teacher(offering.getTeacher())
                        .startTimeUtc(startUtc)
                        .endTimeUtc(endUtc)
                        .build();

        Session saved =
                sessionRepository.save(session);

        return CreateSessionResponse.builder()
                .sessionId(saved.getSessionId())
 //               .bookingId(offering.getOfferingId())
                .startTime(saved.getStartTimeUtc().toString())
                .endTime(saved.getEndTimeUtc().toString())
                .build();
    }

    @Override
    public List<CreateSessionResponse> getOfferingSessions(
            Long offeringId) {

        return sessionRepository
                .findByOfferingOfferingId(offeringId)
                .stream()
                .map(session ->
                                CreateSessionResponse.builder()
                                .sessionId(session.getSessionId())
 //                               .bookingId(offeringId)
                                .startTime(
                                        session.getStartTimeUtc()
                                                .toString())
                                .endTime(
                                        session.getEndTimeUtc()
                                                .toString())
                                .build())
                .toList();
    }

    @Override
    public List<CreateSessionResponse> getStudentSessions(
            Long offeringId,
            String timezone) {

        return sessionRepository
                .findByOfferingOfferingId(offeringId)
                .stream()
                .map(session ->
                        CreateSessionResponse.builder()
                                .sessionId(session.getSessionId())
       //                         .bookingId(offeringId)
                                .startTime(
                                        timeZoneUtil
                                                .convertUtcToLocal(
                                                        session.getStartTimeUtc(),
                                                        timezone)
                                                .toString())
                                .endTime(
                                        timeZoneUtil
                                                .convertUtcToLocal(
                                                        session.getEndTimeUtc(),
                                                        timezone)
                                                .toString())
                                .build())
                .toList();
    }
}
