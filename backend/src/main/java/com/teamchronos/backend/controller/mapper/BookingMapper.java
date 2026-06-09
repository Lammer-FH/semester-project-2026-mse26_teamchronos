package com.teamchronos.backend.controller.mapper;

import com.teamchronos.backend.controller.dto.BookingDto;
import com.teamchronos.backend.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDto bookingToBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .roomId(booking.getRoom().getId())
                .roomTitle(booking.getRoom().getTitle())
                .guestId(booking.getGuest().getId())
                .guestEmail(booking.getGuest().getEmail())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .totalPrice(booking.getTotalPrice())
                .build();
    }
}