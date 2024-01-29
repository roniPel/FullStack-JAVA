package ErrorHandling;

public enum Errors {
    SQL_ERROR("Coupon System encountered an SQL error! "),
    USER_ALREADY_EXISTS("The user already exists in the system. "),
    INCORRECT_LOGIN_DETAILS("Incorrect username or password. "),
    THREAD_ERROR("There is a thread/multithreading problem. "),
    EMPTY_OR_NULL("Item is empty or Null. ");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
