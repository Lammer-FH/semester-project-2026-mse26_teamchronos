package com.teamchronos.backend.controller;

import com.teamchronos.backend.controller.dto.RoomDto;
import com.teamchronos.backend.controller.dto.RoomsResponseDto;
import com.teamchronos.backend.controller.mapper.RoomMapper;
import com.teamchronos.backend.service.RoomService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RoomController.BASE_URL)
public class RoomController {

    static final String BASE_URL = "/api/v1/rooms";
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
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
}
