package com.aubay.test.spaceship.factory;

import com.aubay.test.spaceship.model.dto.SpaceshipDTO;

import static com.aubay.test.spaceship.factory.SpaceshipFactory.ID;
import static com.aubay.test.spaceship.factory.SpaceshipFactory.NAME;
import static com.aubay.test.spaceship.factory.SpaceshipFactory.CAPACITY;
import static com.aubay.test.spaceship.factory.SpaceshipFactory.FUEL_CONSUMPTION;

public class SpaceshipDTOFactory {

    public static SpaceshipDTO buildDefault() {
        return new SpaceshipDTO(ID, NAME, CAPACITY, FUEL_CONSUMPTION);
    }
}
