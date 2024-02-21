package ClassExercise_210224.catLab.Exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    ID_NOT_FOUND("Id is not found \n"),
    ID_ALREADY_EXISTS("Id already exists \n"),
    WEIGHT_ERROR("Weight should be a positive number \n");

    private String message;
    Errors(String message) {
        this.message = message;
    }
}
