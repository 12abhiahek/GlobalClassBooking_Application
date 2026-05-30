package com.Booking.BookingApplication.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferingResponse {

    private Long offeringId;
    private String name;
    private String status;

    private String teacherName;

    private String courseName;
}
