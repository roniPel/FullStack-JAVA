package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    protected CouponRepository couponRepo;
    protected CompanyRepository companyRepo;
    protected CustomerRepository customerRepo;

    private int customerId;  // Customer ID belonging to the customer that logged in

    /**
     * Constructor
     * @param customerId customer ID belonging to customer logged on
     */
    public CustomerServiceImpl(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean Login(String email, String password) throws CustomerException, AdminException, CompanyException {
        Customer customer = customerRepo.findByEmailAndPassword(email,password);
        if(customer == null) {
            throw new CustomerException(CustomerErrors.INCORRECT_LOGIN_DETAILS);
        }
        else {
            this.customerId = customer.getA_id();
            return true;
        }
    }

    @Override
    public void PurchaseCoupon() throws CustomerException {

    }

    @Override
    public void GetCustomerCoupons() throws CustomerException {

    }

    @Override
    public void GetCustomerCouponsByCategory() throws CustomerException {

    }

    @Override
    public void GetCustomerCouponsByMaxPrice() throws CustomerException {

    }

    @Override
    public void GetCustomerDetails() throws CustomerException {

    }

    //Todo - write all methods


}
