package com.aubay.test.spaceship.api.controller;

import com.aubay.test.spaceship.api.request.SpaceshipUpdateRequest;
import com.aubay.test.spaceship.api.response.SpaceshipResponse;
import com.aubay.test.spaceship.service.impl.integration.SpaceshipSenderService;
import com.aubay.test.spaceship.model.dto.SpaceshipDTO;
import com.aubay.test.spaceship.service.impl.SpaceshipService;
import com.aubay.test.spaceship.api.request.SpaceshipCreateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/spaceship")
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    @GetMapping
    public Page<SpaceshipResponse> findAll(Pageable pageable) {
        return spaceshipService.findAllSpaceships(pageable);
    }

    @PostMapping
    public SpaceshipResponse create(@RequestBody @Valid SpaceshipCreateRequest request) {
        return spaceshipService.createSpaceship(new SpaceshipDTO(request.name(), request.capacity(), request.fuelConsumption()));
    }

    @PutMapping("/{id}")
    public SpaceshipResponse update(@PathVariable Integer id, @RequestBody @Valid SpaceshipUpdateRequest request) {
        return spaceshipService.updateSpaceship(new SpaceshipDTO(id, null, request.capacity(), request.fuelConsumption()));
    }

    @GetMapping("/{id}")
    public SpaceshipResponse getById(@PathVariable Integer id) {
        return spaceshipService.getSpaceshipById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        spaceshipService.deleteSpaceship(id);
    }

    @GetMapping("/search/{name}")
    public Page<SpaceshipResponse> searchByName(@PathVariable String name, Pageable pageable) {
        return spaceshipService.searchSpaceshipByName(name, pageable);
    }
}
