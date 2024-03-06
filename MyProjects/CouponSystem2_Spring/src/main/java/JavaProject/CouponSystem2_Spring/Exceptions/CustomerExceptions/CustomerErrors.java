package JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions;

import lombok.Getter;

/**
 * CustomerErrors Class (Enum) - Contains Strings of possible errors for Customer users
 */
@Getter
public enum CustomerErrors {
    GENERAL_CUSTOMER_ERROR("\nERROR! There was an error when trying to perform the customer method. \n"),
    INCORRECT_LOGIN_DETAILS("\nERROR! Incorrect customer username and/or password. \n"),
    CUSTOMER_DOES_NOT_EXIST("\nERROR! Customer doesn't exist in the system. \n"),
    DUPLICATE_ENTRY("\nERROR! The value inserted (name/ id/ email/ user) already exists in the system. \n"),
    EMPTY_OR_NULL("\nERROR! The relevant table/field is empty or null. \n"),

    // Coupon related actions
    COUPON_EXISTS_FOR_CUSTOMER("\nERROR! The coupon already exists for this customer. \n"),
    COUPON_AMOUNT_IS_ZERO("\nERROR! There are not enough coupons in the system in order to perform this purchase. \n"),
    COUPON_DATE_EXPIRED("\nERROR! The coupon date has expired. \n"),
    COUPON_DOES_NOT_EXIST("\nERROR! Coupon doesn't exist in the system. \n");

    private String message;
    /**
     * Constructor which inserts the relevant message into each error.
     * @param message String message relevant for each error
     */
    CustomerErrors(String message) {
        this.message = message;
    }
}
