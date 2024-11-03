package com.aubay.test.spaceship.service;

import com.aubay.test.spaceship.exception.SpaceshipNotFoundException;
import com.aubay.test.spaceship.model.dto.SpaceshipDTO;
import com.aubay.test.spaceship.model.entity.Spaceship;
import com.aubay.test.spaceship.repository.SpaceshipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractSpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    protected AbstractSpaceshipService(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    protected Page<Spaceship> findAll(Pageable pageable) {
        return spaceshipRepository.findAll(pageable);
    }

    protected Page<Spaceship> searchByName(String name, Pageable pageable) {
        return spaceshipRepository.searchByNameContaining(name, pageable);
    }

    protected Spaceship getById(Integer spaceShipId) {
        return spaceshipRepository.findById(spaceShipId).orElseThrow(() -> new SpaceshipNotFoundException(spaceShipId));
    }

    protected Spaceship create(SpaceshipDTO dto) {
        Spaceship spaceship = new Spaceship();
        spaceship.merge(dto);
        return spaceshipRepository.save(spaceship);
    }

    protected Spaceship update(SpaceshipDTO dto) {
        Spaceship spaceship = getById(dto.getId());
        spaceship.merge(dto);
        return spaceshipRepository.save(spaceship);
    }

    protected void delete(Integer spaceShipId) {
        spaceshipRepository.deleteById(spaceShipId);
    }
}
