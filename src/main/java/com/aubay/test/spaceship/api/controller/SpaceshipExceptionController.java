package com.aubay.test.spaceship.api.controller;

import com.aubay.test.spaceship.api.response.SpaceshipErrorResponse;
import com.aubay.test.spaceship.exception.SpaceshipNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpaceshipExceptionController {

    @ExceptionHandler(SpaceshipNotFoundException.class)
    public ResponseEntity<SpaceshipErrorResponse> spaceshipNotFoundException(SpaceshipNotFoundException e) {
        return new ResponseEntity<>(
                new SpaceshipErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                ),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SpaceshipErrorResponse> bodyRequestNotValid(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new SpaceshipErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getBody().getDetail(),
                        System.currentTimeMillis()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SpaceshipErrorResponse> unknownException(Exception e) {
        return new ResponseEntity<>(
                new SpaceshipErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        System.currentTimeMillis()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
