package com.sharf.timur.dto.subscription;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubscriptionRequest (

        @NotEmpty(message = "name sub is mandatory")
        @NotNull(message = "name sub is mandatory")
        String name
) {
}
