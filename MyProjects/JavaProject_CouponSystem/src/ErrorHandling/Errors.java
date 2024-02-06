package ErrorHandling;

public enum Errors {

    // Database errors
    SQL_ERROR("Coupon System encountered an SQL error! \n"),
    DUPLICATE_ENTRY("The value inserted (name/ email/ user) already exist in the system. \n"),

    // General errors
    TABLE_IS_EMPTY("The requested table is empty. \n"),
    INCORRECT_LOGIN_DETAILS("Incorrect username and/or password. \n"),
    THREAD_ERROR("There is a thread/multithreading problem. \n"),
    EMPTY_OR_NULL("Item is empty or Null. \n"),

    // Company errors
    COMPANY_DOES_NOT_EXIST("Company doesn't exist in the system. \n"),
    COMPANY_NAME_ALREADY_EXISTS("The Company name already exists in the system. \n"),

    // Customer errors
    CUSTOMER_DOES_NOT_EXIST("Customer doesn't exist in the system. \n"),

    // Coupon errors
    COUPON_DOES_NOT_EXIST("Coupon doesn't exist in the system. \n");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
