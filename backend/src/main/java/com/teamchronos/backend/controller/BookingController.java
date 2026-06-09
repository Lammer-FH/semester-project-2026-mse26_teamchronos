package com.teamchronos.backend.controller;

import com.teamchronos.backend.controller.dto.BookingDto;
import com.teamchronos.backend.controller.dto.CreateBookingDto;
import com.teamchronos.backend.controller.mapper.BookingMapper;
import com.teamchronos.backend.entity.Booking;
import com.teamchronos.backend.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = BookingController.BASE_URL)
@CrossOrigin(origins = "*")
public class BookingController {

    static final String BASE_URL = "/api/v1/bookings";

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody CreateBookingDto request) {
        Map<String, Object> response = new HashMap<>();

        // Same date rule the availability endpoint uses
        if (!request.getCheckInDate().isBefore(request.getCheckOutDate())) {
            response.put("error", "Check-out must be later than check-in");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!bookingService.isRoomAvailable(request.getRoomId(), request.getCheckInDate(), request.getCheckOutDate())) {
            response.put("error", "Room is not available for the selected dates");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        Booking booking = bookingService.createBooking(request);
        return new ResponseEntity<>(bookingMapper.bookingToBookingDto(booking), HttpStatus.CREATED);
    }
}