package com.aubay.test.spaceship.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SpaceshipUpdateRequest(
        @NotNull @Min(0) Integer capacity,
        @NotNull @Min(0) Double fuelConsumption) {
}
