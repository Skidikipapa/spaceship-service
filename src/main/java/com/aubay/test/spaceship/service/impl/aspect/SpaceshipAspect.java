package com.aubay.test.spaceship.service.impl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SpaceshipAspect {

    private static final Logger logger = LoggerFactory.getLogger(SpaceshipAspect.class);

    @Before(value = "execution(* com.aubay.test.spaceship.api.controller.SpaceshipController.getById(Integer))")
    public void getByNegativeId(JoinPoint joinPoint) {
        Integer id = (Integer) joinPoint.getArgs()[0];

        if (id != null && id < 0) {
            logger.info("Negative id received when retrieving a spaceship: {}", id);
        }
    }
}
