package com.aubay.test.spaceship.api.response;

public record SpaceshipErrorResponse(int httpStatusCode, String message, long timestamp) {
}
