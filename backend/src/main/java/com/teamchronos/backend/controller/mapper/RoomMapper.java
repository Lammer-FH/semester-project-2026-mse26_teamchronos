package com.teamchronos.backend.controller.mapper;

import com.teamchronos.backend.controller.dto.RoomDto;
import com.teamchronos.backend.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDto roomToRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .title(room.getTitle())
                .description(room.getDescription())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .build();
    }

    public Room roomDtoToRoom(RoomDto roomDto) {
        return Room.builder()
                .title(roomDto.getTitle())
                .description(roomDto.getDescription())
                .capacity(roomDto.getCapacity())
                .pricePerNight(roomDto.getPricePerNight())
                .build();
    }
}
