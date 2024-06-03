package com.example.BackEnd_Spring.Exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    TASK_ALREADY_EXISTS("The task already exists. \n"),
    TASK_NOT_FOUND("The task is not found. \n"),
    PERSON_ALREADY_EXISTS("The person responsible for the task already exists in the system. \n"),
    PERSON_NOT_FOUND("The person responsible for the task is not found. \n");

    private final String message;
    Errors(String message) {
        this.message = message;
    }
}
