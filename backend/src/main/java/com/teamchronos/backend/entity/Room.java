package com.teamchronos.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rooms_extras",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "extraId")
    )
    private List<Extra> extras;
}