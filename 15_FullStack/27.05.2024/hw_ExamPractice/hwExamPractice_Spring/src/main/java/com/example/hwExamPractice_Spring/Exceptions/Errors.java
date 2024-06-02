package com.example.hwExamPractice_Spring.Exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    ID_NOT_FOUND("The task does not exist in the system. \n"),
    ID_ALREADY_EXISTS("The task already exists in the system");

    private final String message;
    Errors(String message) {
        this.message = message;
    }
}
