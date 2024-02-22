package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;

public class CustomersServiceImpl implements CustomersService{
    @Autowired
    private CustomerRepository customerRepo;

    //Todo - write all methods

    @Override
    public boolean IsCustomerExists(String email, String password) throws CustomerException {
        return false;
    }

    @Override
    public int GetCustomerIDByEmail(String email) throws CustomerException {
        return 0;
    }

    @Override
    public boolean IsCustomerIdExists(int customerID) throws AdminException {
        return false;
    }

    @Override
    public boolean AddCustomer(Customer customer) throws AdminException {
        return false;
    }

    @Override
    public boolean UpdateCustomer(Customer customer) throws AdminException {
        return false;
    }

    @Override
    public boolean DeleteCustomer(int customerID) throws AdminException {
        return false;
    }

    @Override
    public ArrayList<Customer> GetAllCustomers() throws AdminException {
        return null;
    }

    @Override
    public Customer GetOneCustomer(int customerID) throws AdminException, CustomerException {
        return null;
    }

    @Override
    public Map<Integer, Integer> CustomerIDsVScouponIDs() throws AdminException {
        return null;
    }
}
