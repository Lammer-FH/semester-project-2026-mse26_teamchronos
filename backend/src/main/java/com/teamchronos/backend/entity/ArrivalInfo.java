package com.teamchronos.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Table(name = "arrival_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookingId")
    private Booking booking;

    private LocalTime estimatedArrivalTime;

    private String flightNumber;

    private Boolean requiresAirportTransfer;

    @Column(length = 1000)
    private String specialRequests;
}