package com.teamchronos.backend.controller;

import com.teamchronos.backend.controller.dto.RoomDto;
import com.teamchronos.backend.controller.dto.RoomsResponseDto;
import com.teamchronos.backend.controller.mapper.RoomMapper;
import com.teamchronos.backend.service.RoomService;
import com.teamchronos.backend.service.BookingService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = RoomController.BASE_URL)
@CrossOrigin(origins = "*")
public class RoomController {

    static final String BASE_URL = "/api/v1/rooms";
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final BookingService bookingService;

    @Autowired
    public RoomController(RoomService roomService, RoomMapper roomMapper, BookingService bookingService) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
        this.bookingService = bookingService;
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public RoomsResponseDto findPaginated(@RequestParam("pageIndex") @PositiveOrZero int page,
                                          @RequestParam("pageSize") @Positive int size) {
        return RoomsResponseDto.builder()
                .rooms(
                        roomService.findPaginated(page, size)
                                .map(roomMapper::roomToRoomDto)
                                .getContent()
                )
                .build();
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getRoomAvailability(
            @PathVariable("id") Long roomId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        Map<String, Object> response = new HashMap<>();
        LocalDate start;
        LocalDate end;

        try {
            start = LocalDate.parse(startDate);
            end = LocalDate.parse(endDate);
        } catch (Exception e) {
            response.put("error", "400 Bad Request: Date format must be yyyy-MM-dd");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!start.isBefore(end)) {
            response.put("error", "Check-out must be later than check-in");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean available = bookingService.isRoomAvailable(roomId, start, end);

        response.put("id", roomId);
        response.put("available", available);

        if (!available) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("id") Long id) {
        return roomService.findById(id)
                .map(room -> new ResponseEntity<>(roomMapper.roomToRoomDto(room), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}