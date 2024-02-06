package ErrorHandling;

public enum Errors {

    // Database errors
    SQL_ERROR("Coupon System encountered an SQL error! "),
    DUPLICATE_ENTRY("The value inserted (name/ email/ user) already exist in the system. "),

    // General errors
    TABLE_IS_EMPTY("The requested table is empty. "),
    ITEM_DOES_NOT_EXIST("Item doesn't exist in the system. "),
    INCORRECT_LOGIN_DETAILS("Incorrect username and/or password. "),
    THREAD_ERROR("There is a thread/multithreading problem. "),
    EMPTY_OR_NULL("Item is empty or Null. "),

    // Company errors
    COMPANY_DOES_NOT_EXIST("Company doesn't exist in the system. "),
    COMPANY_NAME_ALREADY_EXISTS("The Company name already exists in the system. "),

    // Customer errors

    CUSTOMER_DOES_NOT_EXIST("Customer doesn't exist in the system. ");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
