package com.example.Exercise_BackEnd.Exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    MEETING_ALREADY_EXISTS("Meeting already exists. \n"),
    TEAM_DOES_NOT_EXIST("The team does not exist. \n"),
    TEAM_ALREADY_EXISTS("The team already exists. \n");

    private String message;

    Errors(String message) {
        this.message = message;
    }
}
