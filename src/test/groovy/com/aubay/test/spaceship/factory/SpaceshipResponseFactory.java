package com.aubay.test.spaceship.factory;

import com.aubay.test.spaceship.api.response.SpaceshipResponse;

import static com.aubay.test.spaceship.factory.SpaceshipFactory.ID;
import static com.aubay.test.spaceship.factory.SpaceshipFactory.NAME;
import static com.aubay.test.spaceship.factory.SpaceshipFactory.CAPACITY;
import static com.aubay.test.spaceship.factory.SpaceshipFactory.FUEL_CONSUMPTION;

public class SpaceshipResponseFactory {

    public static SpaceshipResponse buildDefault() {
        return new SpaceshipResponse(ID, NAME, CAPACITY, FUEL_CONSUMPTION);
    }
}
