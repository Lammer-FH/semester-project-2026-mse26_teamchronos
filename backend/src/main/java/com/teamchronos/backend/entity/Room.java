package com.teamchronos.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Positive
    private Integer capacity;

    @PositiveOrZero
    private Float pricePerNight;

    private String imagePath;

    @ManyToMany
    @JoinTable(
            name = "room_extras",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private Set<Extra> extras = new HashSet<>();
}