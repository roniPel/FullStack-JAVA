package JavaProject.CouponSystem2_Spring.Services;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;

/**
 * Client Service Interface - Used as a base for all User (client) types in the system
 */
public interface ClientService {
    /**
     * Used as a base for all user (client) types
     * @param email email (user) used for login
     * @param password password used for login
     * @return true id login succeeded, false if login failed
     * @throws AdminException If we get any exception.  Details are provided
     * @throws CompanyException If we get any exception.  Details are provided
     * @throws CustomerException If we get any exception.  Details are provided
     * @throws GuestException If we get any exception.  Details are provided
     */
    boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException, GuestException;
}
