package com.teamchronos.backend.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class RoomsResponseDto {
    List<RoomDto> rooms;
}