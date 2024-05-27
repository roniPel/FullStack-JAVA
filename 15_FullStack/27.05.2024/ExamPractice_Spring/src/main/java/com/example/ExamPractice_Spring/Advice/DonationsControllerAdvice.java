package com.example.ExamPractice_Spring.Advice;

import com.example.ExamPractice_Spring.Exceptions.DonationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.security.auth.login.LoginException;

@RestControllerAdvice
public class DonationsControllerAdvice {
    @ExceptionHandler(value = {DonationException.class, LoginException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails HandleError(Exception exception) {
        return new ErrorDetails("Donations Error",exception.getMessage());
    }

}
