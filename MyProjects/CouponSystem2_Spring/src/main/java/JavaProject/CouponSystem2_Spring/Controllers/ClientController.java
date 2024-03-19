package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;

/**
 * Controller for All User types
 */
//Todo - which annotations to use? @RestController?
public abstract class ClientController {
    /**
     * Checks weather user login details are correct
     * @param email The email for login.
     * @param password The password for login.
     * @return True if succeeded, False if failed
     */
    abstract boolean login(String email, String password);
}
