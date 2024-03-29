package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * Test Methods Class - Contains basic methods used for Testing all user functionalities
 */
@Component
public class TestMethods {
    public double maxPrice = 200;

    /**
     * Provides a random integer based on provided param
     * @param range range used to provide random integer
     * @return random integer
     */
    public int GetrandInt(int range) {
        return (int)(Math.random()*(range));
    }

    /**
     * Provides a random Id from a provided list of objects
     * @param myList List of objects to choose from
     * @return a random ID belonging to one of the objects in the provided list
     */
    public int GetRandIdFromList(List<?> myList) {
        int randIdx = (int)(Math.random()*(myList.size()));
        if(myList.size() == 1){
            randIdx = 0;
        }
        Object firstObject = myList.get(0);
        if(firstObject instanceof Customer) {
            return ((Customer) myList.get(randIdx)).getId();
        }
        else if(firstObject instanceof Company){
            return ((Company) myList.get(randIdx)).getId();
        }
        else if (firstObject instanceof Coupon) {
            return ((Coupon) myList.get(randIdx)).getId();
        }
        return -1;
    }

    /**
     * Checks weather user login is correct
     * @param email The email for login.
     * @param password The password for login.
     * @param clientService The selected client service type for the login.
     * @return True if login succeeded, false if login failed.
     * @throws AdminException If we get any exception.  Details are provided
     * @throws CompanyException If we get any exception.  Details are provided
     * @throws CustomerException If we get any exception.  Details are provided
     * @throws GuestException If we get any exception.  Details are provided
     */
    public boolean CheckLogin(String email, String password, ClientService clientService)
    //Todo - change method (or user Login Service/controller)
            throws AdminException, CompanyException, CustomerException, GuestException, LoginException {
        String token = clientService.Login(email, password);
        return true;
    }
}
