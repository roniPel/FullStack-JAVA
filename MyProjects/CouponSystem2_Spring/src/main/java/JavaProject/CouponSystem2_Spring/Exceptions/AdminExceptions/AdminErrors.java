package JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions;

import lombok.Getter;

/**
 * AdminErrors Class (Enum) - Contains Strings of possible errors for Admin users
 */
@Getter
public enum AdminErrors {

    GENERAL_ADMIN_ERROR("\nERROR! There was an error when trying to perform the admin method. \n"),
    INCORRECT_LOGIN_DETAILS("\nERROR! Incorrect admin username and/or password. \n"),
    DUPLICATE_ENTRY("\nERROR! The value inserted (id/ name/ email/ user) already exists in the system. \n"),

    // Actions on Company
    COMPANY_DOES_NOT_EXIST("\nERROR! Company doesn't exist in the system. \n"),
    COMPANY_NAME_ALREADY_EXISTS("\nERROR! The company name already exists in the system. \n"),
    COMPANY_EMAIL_ALREADY_EXISTS("\nERROR! The company email already exists in the system. \n"),
    CANT_UPDATE_COMPANY_ID("\nERROR! Update of company ID is not allowed. \n"),
    CANT_UPDATE_COMPANY_NAME("\nERROR! Update of company Name is not allowed. \n"),

    // Actions on Customer
    CUSTOMER_DOES_NOT_EXIST("\nERROR! Customer doesn't exist in the system. \n"),
    CUSTOMER_EMAIL_ALREADY_EXISTS("\nERROR! The customer email already exists in the system. \n"),
    CANT_UPDATE_CUSTOMER_ID("\nERROR! Update of customer ID is not allowed. \n"),;


    private String message;

    /**
     * Constructor which inserts the relevant message into each error.
     * @param message String message relevant for each error
     */
    AdminErrors(String message) {
        this.message = message;
    }
}
