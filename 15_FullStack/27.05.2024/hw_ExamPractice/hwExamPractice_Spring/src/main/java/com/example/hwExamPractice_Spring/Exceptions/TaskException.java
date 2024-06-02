package com.example.hwExamPractice_Spring.Exceptions;


public class TaskException extends Exception{
    public TaskException(Errors errors) {
        super(errors.getMessage());
    }
}
