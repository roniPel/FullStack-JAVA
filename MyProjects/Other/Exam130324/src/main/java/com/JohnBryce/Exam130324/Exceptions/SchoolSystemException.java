package com.JohnBryce.Exam130324.Exceptions;

public class SchoolSystemException extends Exception{
    public SchoolSystemException(Errors error) {
        super(error.getMessage());
    }
}
