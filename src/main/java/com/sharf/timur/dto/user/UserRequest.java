package com.sharf.timur.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRequest(

        @NotEmpty(message = "name is mandatory")
        @NotNull(message = "name is mandatory")
        String firstName,

        @NotEmpty(message = "last name is mandatory")
        @NotNull(message = "last name is mandatory")
        String lastName,

        @Nullable
        String surName,

        @NotNull(message = "date of birth is mandatory")
        LocalDate dateOfBirth,

        @Email(message = "invalid mail format")
        @NotEmpty(message = "email is mandatory")
        @NotNull(message = "email is mandatory")
        String email
) {
}
