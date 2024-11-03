package com.aubay.test.spaceship.exception;

public class SpaceshipNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Spaceship with id { %d } not found.";

    public SpaceshipNotFoundException(Integer spaceshipId) {
        super(String.format(MESSAGE, spaceshipId));
    }
}
