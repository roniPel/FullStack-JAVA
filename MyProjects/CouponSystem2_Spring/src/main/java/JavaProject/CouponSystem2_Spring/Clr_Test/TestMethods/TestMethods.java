package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TestMethods {
    public double maxPrice = 200;
    public int GetrandInt(int range) {
        return (int)(Math.random()*(range));
    }

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
     * @param email The email for login.
     * @param password The password for login.
     * @param clientService The selected client service type for the login.
     * @return True if login succeeded, false if login failed.
     * @throws AdminException,CompanyException,CustomerException If we get any exception.  Details are provided
     */
    public boolean CheckLogin(String email, String password, ClientService clientService)
            throws AdminException,CompanyException,CustomerException {
        return clientService.Login(email, password);
    }
}
