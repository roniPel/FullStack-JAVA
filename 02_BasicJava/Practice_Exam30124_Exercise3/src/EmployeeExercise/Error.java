package EmployeeExercise;

public enum Error {
    NULL_OR_EMPTY("the list is null or empty"),EMPLOYEE_NOT_FOUND("the given employee not found"),
    EMPLOYEE_ALREADY_EXIST("the given employee already exists");

    final String message;

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
