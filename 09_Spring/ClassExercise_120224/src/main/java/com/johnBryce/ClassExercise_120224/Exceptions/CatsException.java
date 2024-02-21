package com.johnBryce.ClassExercise_120224.ErrorManagement;


public class CatsException extends Exception{
    public CatsException(Errors errors) {
        super(errors.getMessage());
    }
}
