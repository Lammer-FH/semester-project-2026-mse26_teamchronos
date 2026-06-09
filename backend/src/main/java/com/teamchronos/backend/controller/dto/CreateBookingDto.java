package com.teamchronos.backend.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class CreateBookingDto {

    @NotNull
    Long roomId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Email
    String email;

    @NotNull
    LocalDate checkInDate;

    @NotNull
    LocalDate checkOutDate;

    boolean breakfastIncluded;
}