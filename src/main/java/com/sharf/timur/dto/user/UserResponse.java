package com.sharf.timur.dto.user;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(
        String firstName,
        String lastName,
        String surName,
        LocalDate dateOfBirth,
        String email
) {
}
