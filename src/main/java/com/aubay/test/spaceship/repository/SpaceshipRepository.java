package com.aubay.test.spaceship.repository;

import com.aubay.test.spaceship.model.entity.Spaceship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface SpaceshipRepository extends JpaRepositoryImplementation<Spaceship, Integer> {

    Page<Spaceship> searchByNameContaining(String name, Pageable pageable);
}
