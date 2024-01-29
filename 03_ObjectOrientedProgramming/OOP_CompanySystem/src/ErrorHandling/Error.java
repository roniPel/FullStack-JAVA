package ErrorHandling;

public enum Error {
    NULL_OR_EMPTY("the list is null or empty"), EMPLOYEE_NOT_FOUND("the given employee is not found"),
    EMPLOYEE_ALREADY_EXISTS("the given employee already exists");

    // Attributes
    private final String message;

    // Constructors

    Error(String message) {
        this.message = message;
    }

    // Methods
    public String getMessage() {
        return this.message;
    }
}
