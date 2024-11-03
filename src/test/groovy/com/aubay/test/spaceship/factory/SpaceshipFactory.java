package com.aubay.test.spaceship.factory;

import com.aubay.test.spaceship.model.entity.Spaceship;

public class SpaceshipFactory {

    public static final int ID = 1;
    public static final String NAME = "Spaceship";
    public static final int CAPACITY = 2;
    public static final double FUEL_CONSUMPTION = 1.0;

    public static Spaceship buildDefault() {
        Spaceship spaceship = new Spaceship();
        spaceship.setId(ID);
        spaceship.setName(NAME);
        spaceship.setCapacity(CAPACITY);
        spaceship.setFuelConsumption(FUEL_CONSUMPTION);
        return spaceship;
    }
}
