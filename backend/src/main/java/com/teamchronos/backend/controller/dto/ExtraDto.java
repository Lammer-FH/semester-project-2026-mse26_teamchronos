package com.teamchronos.backend.controller.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ExtraDto {
    Long id;
    String name;
    String icon;
}