package com.johnBryce.ClassExercise_120224.Exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    ID_NOT_FOUND("ID was not found \n"),
    ID_ALREADY_EXISTS("The ID already exists. \n"),
    WEIGHT_ERROR("Weight should contain a positive number. \n");

    private final String message;
    Errors(String message) {
        this.message = message;
    }
}
