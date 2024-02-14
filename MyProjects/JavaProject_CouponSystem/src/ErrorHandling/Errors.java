package ErrorHandling;

/**
 * Enum containing the errors that exist in the System
 */
public enum Errors {

    // Database errors
    SQL_ERROR("Coupon System encountered an SQL error! \n"),
    DUPLICATE_ENTRY("The value inserted (name/ email/ user) already exists in the system. \n"),
    TABLE_IS_EMPTY("The requested table is empty. \n"),

    // General errors
    INCORRECT_LOGIN_DETAILS("Incorrect username and/or password. \n"),
    THREAD_ERROR("There is a thread/multithreading problem. \n"),
    EMPTY_OR_NULL("Item is empty or Null. \n"),
    GENERAL_SYSTEM_ERROR("The coupon system encountered an error.  Please find details below: \n"),

    // Company errors
    COMPANY_DOES_NOT_EXIST("Company doesn't exist in the system. \n"),
    COMPANY_NAME_ALREADY_EXISTS("The Company name already exists in the system. \n"),

    // Customer errors
    CUSTOMER_DOES_NOT_EXIST("Customer doesn't exist in the system. \n"),

    // Coupon errors
    COUPON_EXISTS_FOR_CUSTOMER("The coupon already exists for this customer. \n"),
    COUPON_EXISTS_FOR_COMPANY("The coupon already exists for this company. \n"),
    COUPON_DOES_NOT_BELONG_TO_COMPANY("The coupon doesn't belong to this company. \n"),
    COUPON_COMPANY_ID_INCORRECT("The coupon's company ID doesn't match login details. \n"),
    COUPON_AMOUNT_IS_ZERO("There are not enough coupons in the system in order to perform this purchase. \n"),
    COUPON_DATE_EXPIRED("The coupon date has expired. \n"),
    COUPON_DOES_NOT_EXIST("Coupon doesn't exist in the system. \n");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
