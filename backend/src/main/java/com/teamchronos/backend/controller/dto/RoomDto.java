package com.teamchronos.backend.controller.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RoomDto {
    Long id;
    String title;
    String description;
    Integer capacity;
    Float pricePerNight;
}
