package com.JohnBryce.Exam130324.Exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    ID_NOT_FOUND("The student does not exist!\n"),
    ID_ALREADY_EXISTS("The student already exists!\n");

    private String message;

    Errors(String message) {
        this.message = message;
    }
}
