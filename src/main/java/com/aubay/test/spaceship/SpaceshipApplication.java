package com.aubay.test.spaceship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class SpaceshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceshipApplication.class, args);
    }

}
