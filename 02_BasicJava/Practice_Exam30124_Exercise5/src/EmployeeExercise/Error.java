package EmployeeExercise;

public enum Error {
    NULL_OR_EMPTY("the list is null or empty"),EMPLOYEE_NOT_FOUND("the given employee not found"),
    EMPLOYEE_ALREADY_EXIST("the given employee already exists"),
    FILE_READ_PROBLEM("reading from file went wrong"), FILE_WRITE_PROBLEM("writing to the file went wrong");

    final String message;

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
