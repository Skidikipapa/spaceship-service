package com.aubay.test.spaceship.service.impl;

import com.aubay.test.spaceship.api.response.SpaceshipResponse;
import com.aubay.test.spaceship.model.dto.SpaceshipDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SpaceshipResponseService {

    public Page<SpaceshipResponse> buildResponse(Page<SpaceshipDTO> dtoPage) {
        return dtoPage.map(this::buildResponse);
    }

    public SpaceshipResponse buildResponse(SpaceshipDTO dto) {
        return new SpaceshipResponse(dto.getId(), dto.getName(), dto.getCapacity(), dto.getFuelConsumption());
    }
}
