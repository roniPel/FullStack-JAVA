package com.example.Exercise_BackEnd.Exceptions;

public class MeetingException extends Exception{
    public MeetingException(Errors errors) {
        super(errors.getMessage());
    }
}
