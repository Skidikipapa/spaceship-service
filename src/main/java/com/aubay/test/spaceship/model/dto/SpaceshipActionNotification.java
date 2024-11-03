package com.aubay.test.spaceship.model.dto;

import com.aubay.test.spaceship.model.domain.SpaceshipAction;

public record SpaceshipActionNotification(SpaceshipDTO spaceshipDTO, SpaceshipAction spaceshipAction) {
}
