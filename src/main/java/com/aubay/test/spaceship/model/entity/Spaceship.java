package com.aubay.test.spaceship.model.entity;

import com.aubay.test.spaceship.model.dto.SpaceshipDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "SPACESHIP", schema = "spaceship")
@Getter
@Setter
public class Spaceship {

    private static final String SEQUENCE_NAME = "spaceship_sequence";
    private static final String SEQUENCE_GENERATOR = "spaceship_sequence_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Double fuelConsumption;

    public SpaceshipDTO toDTO() {
        return new SpaceshipDTO(id, name, capacity, fuelConsumption);
    }

    public void merge(SpaceshipDTO dto) {
        if (dto.getName() != null) {
            setName(dto.getName());
        }

        if (dto.getCapacity() != null) {
            setCapacity(dto.getCapacity());
        }

        if (dto.getFuelConsumption() != null) {
            setFuelConsumption(dto.getFuelConsumption());
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship spaceship)) return false;
        return getId() != null && Objects.equals(getId(), spaceship.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }
}
