package com.teamchronos.backend.controller.dto;

import com.teamchronos.backend.entity.Extra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long id;
    private String title;
    private String description;
    private Integer capacity;
    private Float pricePerNight;
    private String imagePath;
    private List<Extra> extras;
}