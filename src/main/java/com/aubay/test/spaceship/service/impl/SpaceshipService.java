package com.aubay.test.spaceship.service.impl;

import com.aubay.test.spaceship.api.response.SpaceshipResponse;
import com.aubay.test.spaceship.model.dto.SpaceshipDTO;
import com.aubay.test.spaceship.model.entity.Spaceship;
import com.aubay.test.spaceship.repository.SpaceshipRepository;
import com.aubay.test.spaceship.service.AbstractSpaceshipService;
import com.aubay.test.spaceship.service.impl.integration.SpaceshipSenderService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpaceshipService extends AbstractSpaceshipService {

    private final SpaceshipResponseService spaceshipResponseService;
    private final SpaceshipSenderService spaceshipSenderService;

    public SpaceshipService(SpaceshipRepository spaceshipRepository, SpaceshipResponseService spaceshipResponseService, SpaceshipSenderService spaceshipSenderService) {
        super(spaceshipRepository);
        this.spaceshipResponseService = spaceshipResponseService;
        this.spaceshipSenderService = spaceshipSenderService;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "spaceships")
    public Page<SpaceshipResponse> findAllSpaceships(Pageable pageable) {
        Page<SpaceshipDTO> dtoPage = super.findAll(pageable).map(Spaceship::toDTO);
        return spaceshipResponseService.buildResponse(dtoPage);
    }

    @Transactional(readOnly = true)
    public Page<SpaceshipResponse> searchSpaceshipByName(String name, Pageable pageable) {
        Page<SpaceshipDTO> dtoPage = super.searchByName(name, pageable).map(Spaceship::toDTO);
        return spaceshipResponseService.buildResponse(dtoPage);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "spaceships", key = "#spaceShipId")
    public SpaceshipResponse getSpaceshipById(Integer spaceShipId) {
        SpaceshipDTO dto = super.getById(spaceShipId).toDTO();
        return spaceshipResponseService.buildResponse(dto);
    }

    @Transactional
    public SpaceshipResponse createSpaceship(SpaceshipDTO dto) {
        SpaceshipDTO newDto = super.create(dto).toDTO();
        spaceshipSenderService.sendCreate(newDto);
        return spaceshipResponseService.buildResponse(newDto);
    }

    @Transactional
    @CachePut(value = "spaceships", key = "#dto.id")
    public SpaceshipResponse updateSpaceship(SpaceshipDTO dto) {
        SpaceshipDTO updatedDto = super.update(dto).toDTO();
        spaceshipSenderService.sendUpdate(updatedDto);
        return spaceshipResponseService.buildResponse(updatedDto);
    }

    @Transactional
    @CacheEvict(value = "spaceships", key = "#spaceShipId")
    public void deleteSpaceship(Integer spaceShipId) {
        SpaceshipDTO toDeleteDto = super.getById(spaceShipId).toDTO();
        super.delete(spaceShipId);
        spaceshipSenderService.sendDelete(toDeleteDto);
    }
}
