package com.teamchronos.backend.service;

import com.teamchronos.backend.controller.dto.CreateBookingDto;
import com.teamchronos.backend.entity.Booking;
import com.teamchronos.backend.entity.Guest;
import com.teamchronos.backend.entity.Room;
import com.teamchronos.backend.repository.BookingRepository;
import com.teamchronos.backend.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final RoomService roomService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          GuestRepository guestRepository,
                          RoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.roomService = roomService;
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

    @Transactional
    public Booking createBooking(CreateBookingDto request) {
        Room room = roomService.findById(request.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + request.getRoomId()));

        if (!isRoomAvailable(request.getRoomId(), request.getCheckInDate(), request.getCheckOutDate())) {
            throw new IllegalStateException("Room is not available for the selected dates");
        }

        Guest guest = guestRepository.findByEmail(request.getEmail())
                .orElseGet(() -> guestRepository.save(Guest.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .build()));

        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        float totalPrice = room.getPricePerNight() * nights;

        return bookingRepository.save(Booking.builder()
                .room(room)
                .guest(guest)
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .totalPrice(totalPrice)
                .breakfastIncluded(request.isBreakfastIncluded())
                .build());
    }
}