package ErrorHandling;

/**
 * Enum containing the errors that exist in the System
 */
public enum Errors {

    // Database errors
    SQL_ERROR("ERROR! Coupon System encountered an error when running a query in the SQL DB! \n"),
    DUPLICATE_ENTRY("ERROR! The value inserted (name/ email/ user) already exists in the system. \n"),
    TABLE_IS_EMPTY("ERROR! The requested table is empty. \n"),

    // General errors
    INCORRECT_LOGIN_DETAILS("ERROR! Incorrect username and/or password. \n"),
    THREAD_ERROR("ERROR! There is a thread/multithreading problem. \n"),
    EMPTY_OR_NULL("ERROR! Item is empty or Null. \n"),
    GENERAL_SYSTEM_ERROR("ERROR! The coupon system encountered an error.  Please find details below: \n"),

    // Company errors
    COMPANY_DOES_NOT_EXIST("ERROR! Company doesn't exist in the system. \n"),
    COMPANY_NAME_ALREADY_EXISTS("ERROR! The Company name already exists in the system. \n"),

    // Customer errors
    CUSTOMER_DOES_NOT_EXIST("ERROR! Customer doesn't exist in the system. \n"),

    // Coupon errors
    COUPON_EXISTS_FOR_CUSTOMER("ERROR! The coupon already exists for this customer. \n"),
    COUPON_EXISTS_FOR_COMPANY("ERROR! The coupon already exists for this company. \n"),
    COUPON_DOES_NOT_BELONG_TO_COMPANY("ERROR! The coupon doesn't belong to this company. \n"),
    COUPON_COMPANY_ID_INCORRECT("ERROR! The coupon's company ID doesn't match login details. \n"),
    COUPON_AMOUNT_IS_ZERO("ERROR! There are not enough coupons in the system in order to perform this purchase. \n"),
    COUPON_DATE_EXPIRED("ERROR! The coupon date has expired. \n"),
    COUPON_DOES_NOT_EXIST("ERROR! Coupon doesn't exist in the system. \n");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
