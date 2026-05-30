package com.teamchronos.backend.controller.mapper;

import com.teamchronos.backend.controller.dto.ExtraDto;
import com.teamchronos.backend.controller.dto.RoomDto;
import com.teamchronos.backend.entity.Room;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public RoomDto roomToRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .title(room.getTitle())
                .description(room.getDescription())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .image(room.getImagePath())
                .extras(room.getExtras().stream()
                        .map(extra -> ExtraDto.builder()
                                .id(extra.getId())
                                .name(extra.getName())
                                .icon(extra.getIcon())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
