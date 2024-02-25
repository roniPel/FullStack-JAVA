package JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions;

import lombok.Getter;

@Getter
public enum AdminErrors {

    GENERAL_ADMIN_ERROR("ERROR! There was an error when trying to perform the admin method. \n"),
    INCORRECT_LOGIN_DETAILS("ERROR! Incorrect admin username and/or password. \n"),
    DUPLICATE_ENTRY("ERROR! The value inserted (id/ name/ email/ user) already exists in the system. \n"),

    // Actions on Company
    COMPANY_DOES_NOT_EXIST("ERROR! Company doesn't exist in the system. \n"),
    COMPANY_NAME_ALREADY_EXISTS("ERROR! The company name already exists in the system. \n"),
    COMPANY_EMAIL_ALREADY_EXISTS("ERROR! The company email already exists in the system. \n"),
    CANT_UPDATE_COMPANY_ID("ERROR! Update of company ID is not allowed. \n"),
    CANT_UPDATE_COMPANY_NAME("ERROR! Update of company Name is not allowed. \n"),

    // Actions on Customer
    CUSTOMER_DOES_NOT_EXIST("ERROR! Customer doesn't exist in the system. \n"),
    CUSTOMER_EMAIL_ALREADY_EXISTS("ERROR! The customer email already exists in the system. \n"),
    CANT_UPDATE_CUSTOMER_ID("ERROR! Update of customer ID is not allowed. \n"),;

    private String message;
    AdminErrors(String message) {
        this.message = message;
    }
}
