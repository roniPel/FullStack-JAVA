package JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions;

import lombok.Getter;

@Getter
public enum CustomerErrors {
    GENERAL_CUSTOMER_ERROR("ERROR! There was an error when trying to perform the customer method. \n"),
    INCORRECT_LOGIN_DETAILS("ERROR! Incorrect username and/or password. \n"),
    CUSTOMER_DOES_NOT_EXIST("ERROR! Customer doesn't exist in the system. \n"),
    DUPLICATE_ENTRY("ERROR! The value inserted (name/ id/ email/ user) already exists in the system. \n"),

    // Coupon related actions
    COUPON_EXISTS_FOR_CUSTOMER("ERROR! The coupon already exists for this customer. \n"),
    COUPON_AMOUNT_IS_ZERO("ERROR! There are not enough coupons in the system in order to perform this purchase. \n"),
    COUPON_DATE_EXPIRED("ERROR! The coupon date has expired. \n"),
    COUPON_DOES_NOT_EXIST("ERROR! Coupon doesn't exist in the system. \n");


    private String message;
    CustomerErrors(String message) {
        this.message = message;
    }
}
