package com.nagp.assgnmnt.mservices.config;

import com.nagp.assgnmnt.mServices.model.base.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> genericExceptionHandler(Exception ex)
    {
        log.error("Unknown exception occurred", ex);
        return new ResponseEntity<>(CustomError.builder().code("Unknown").message(ex.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomError> noSuchElementExceptionHandler(NoSuchElementException ex)
    {
        log.error("NoSuchElementException occurred", ex);
        return new ResponseEntity<>(CustomError.builder().code("Not_Found").message(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomError> failedValidationHandler(ValidationException ex)
    {
        log.error("Validation Exception", ex);
        return new ResponseEntity<>(CustomError.builder().code("Invalid request").message(ex.getLocalizedMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomError> illegalArgumentExceptionHandler(IllegalArgumentException ex)
    {
        log.error("IllegalArgumentExceptionHandler:", ex);
        CustomError customError = CustomError.builder().code(ex.getMessage()).message(ex.getMessage()).build();
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }
}
