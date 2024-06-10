package JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions;

import lombok.Getter;

/**
 * UserErrors Class (Enum) - Contains Strings of possible Login errors for All users
 */
@Getter
public enum LoginErrors {

    USER_DOES_NOT_EXIST("\nERROR! The user does not exist in the system. \n"),
    USER_IS_NOT_LOGGED_IN("\nThe user is not logged in. Please try again. \n"),
    USER_ALREADY_EXISTS("\nThe user already exists in the system. \n");

    private String message;

    /**
     * Constructor which inserts the relevant message into each error.
     * @param message String message relevant for each error
     */
    LoginErrors(String message) {
        this.message = message;
    }
}
