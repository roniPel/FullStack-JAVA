package JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions;

import lombok.Getter;

@Getter
public enum AdminErrors {

    GENERAL_ADMIN_ERROR("There was an error when trying to perform the admin method. \n"),
    INCORRECT_LOGIN_DETAILS("ERROR! Incorrect username and/or password. \n"),

    // Actions on Company
    COMPANY_DOES_NOT_EXIST("ERROR! Company doesn't exist in the system. \n"),
    COMPANY_NAME_ALREADY_EXISTS("ERROR! The Company name already exists in the system. \n"),

    // Actions on Customer
    CUSTOMER_DOES_NOT_EXIST("ERROR! Customer doesn't exist in the system. \n");


    private String message;
    AdminErrors(String message) {
        this.message = message;
    }
}
