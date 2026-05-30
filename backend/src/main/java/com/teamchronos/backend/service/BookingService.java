package com.teamchronos.backend.service;

import com.teamchronos.backend.entity.Booking;
import com.teamchronos.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> allBookings = bookingRepository.findAll();

        return allBookings.stream()
                .filter(booking -> booking.getRoom().getId().equals(roomId))
                .noneMatch(booking ->
                        checkInDate.isBefore(booking.getCheckOutDate()) &&
                                checkOutDate.isAfter(booking.getCheckInDate())
                );
    }
}