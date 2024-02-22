package JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions;

import lombok.Getter;

@Getter
public enum CompanyErrors {
    GENERAL_COMPANY_ERROR("ERROR! There was an error when trying to perform the company method. \n"),
    INCORRECT_LOGIN_DETAILS("ERROR! Incorrect username and/or password. \n"),
    DUPLICATE_ENTRY("ERROR! The value inserted (id/ name/ email/ user) already exists in the system. \n"),

    // Coupon related actions
    COUPON_EXISTS_FOR_COMPANY("ERROR! The coupon already exists for this company. \n"),
    COUPON_COMPANY_ID_INCORRECT("ERROR! The coupon's company ID doesn't match login details. \n"),
    COUPON_DOES_NOT_BELONG_TO_COMPANY("ERROR! The coupon doesn't belong to this company. \n");


    private String message;
    CompanyErrors(String message) {
        this.message = message;
    }
}
