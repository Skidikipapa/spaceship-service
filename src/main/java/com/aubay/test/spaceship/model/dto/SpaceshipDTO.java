package com.aubay.test.spaceship.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SpaceshipDTO {

    public SpaceshipDTO(String name, Integer capacity, Double fuelConsumption) {
        this(null, name, capacity, fuelConsumption);
    }

    private Integer id;
    private String name;
    private Integer capacity;
    private Double fuelConsumption;
}
