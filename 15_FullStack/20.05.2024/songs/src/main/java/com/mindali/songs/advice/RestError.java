package com.mindali.songs.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestError {
    @ExceptionHandler(value={JsonProcessingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleJsonException(Exception e){
        return new ErrorDetails("Error",e.getMessage());
    }

    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleGeneralException(Exception e){
        //todo create exception for user errors....
        return new ErrorDetails("Error",e.getMessage());
    }
}
