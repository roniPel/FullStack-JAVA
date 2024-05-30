package JavaProject.CouponSystem2_Spring.Services.LoginService;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;

import javax.security.auth.login.LoginException;

/**
 * Client Service Interface - Used as a base for all User (client) types in the system
 */
public interface LoginService {
    /**
     * Used as a base for all user (client) types
     * @param credentials used for login
     * @return JWT token if login succeeded, null if login failed
     * @throws AdminException If we get any exception.  Details are provided
     * @throws CompanyException If we get any exception.  Details are provided
     * @throws CustomerException If we get any exception.  Details are provided
     * @throws GuestException If we get any exception.  Details are provided
     */
    UserDetails Login(Credentials credentials) throws AdminException, CompanyException, CustomerException, GuestException, LoginException;
}
