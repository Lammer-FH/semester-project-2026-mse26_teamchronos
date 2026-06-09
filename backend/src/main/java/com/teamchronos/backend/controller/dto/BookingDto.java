package com.teamchronos.backend.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class BookingDto {
    Long id;
    Long roomId;
    String roomTitle;
    Long guestId;
    String guestEmail;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Float totalPrice;
    boolean breakfastIncluded;
}