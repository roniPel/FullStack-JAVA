package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    protected CouponRepository couponRepo;
    @Autowired
    protected CompanyRepository companyRepo;
    @Autowired
    protected CustomerRepository customerRepo;

    @Override
    public boolean Login(String email, String password) throws CustomerException, AdminException, CompanyException {
        return false;
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
