package com.JohnBryce.Exam130324.Advice;

import com.JohnBryce.Exam130324.Exceptions.SchoolSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class StudentControllerAdvice {
    @ExceptionHandler(value = {SchoolSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleError(Exception e) {
        return new ErrorDetails("Error!",e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String > handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach( (error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }

}
