package com.Booking.BookingApplication.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferingRequest {
    private Long teacherId;
    private Long courseId;
    private String name;
}
