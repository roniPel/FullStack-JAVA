package JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;

/**
 * LoginException Class - used to manage Login user exceptions
 */
public class LoginException extends Exception{
    /**
     * Constructor for login errors
     * @param loginErrors error received from system
     */
    public LoginException(LoginErrors loginErrors) {
        super(loginErrors.getMessage());
    }
}
