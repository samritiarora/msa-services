package com.nagp.assgnmnt.mservices.config;

import com.nagp.assgnmnt.mservices.model.base.CustomError;
import lombok.extern.slf4j.Slf4j;
import com.nagp.assgnmnt.mservices.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public CustomError unauthorizedExceptionHandler(UnauthorizedException ex)
    {
        return CustomError.builder().code("UnAuthorized").message(ex.getMessage()).build();
    }
}
