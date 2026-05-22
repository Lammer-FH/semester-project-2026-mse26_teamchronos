package com.teamchronos.backend.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class RoomDto {
    Long id;
    String title;
    String description;
    Integer capacity;
    Float pricePerNight;
    String image;
    Set<ExtraDto> extras;
}