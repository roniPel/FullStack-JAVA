package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    protected CouponRepository couponRepo;
    protected CustomerRepository customerRepo;

    private int customerId;  // Customer ID belonging to the customer that logged in
    private Customer loggedCustomer;    // Customer that logged in

    /**
     * Constructor
     * @param customerId customer ID belonging to customer logged on
     */
    public CustomerServiceImpl(int customerId) {
        this.customerId = customerId;
    }

    //Todo - write all methods
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
    public boolean PurchaseCoupon(Coupon coupon) throws CustomerException {
        // Verify coupon exists in DB
        Coupon couponInDb = couponRepo.findById(coupon.getA_id()).orElseThrow(
                () -> new CustomerException(CustomerErrors.COUPON_DOES_NOT_EXIST)
        );
        // Verify coupon exists for customer
        if(customerRepo.existsById(coupon.getA_id())){
            throw new CustomerException(CustomerErrors.COUPON_EXISTS_FOR_CUSTOMER);
        }
        // Verify amount is above 0
        if(couponInDb.getH_amount() <= 0){
            throw new CustomerException(CustomerErrors.COUPON_AMOUNT_IS_ZERO);
        }
        // Verify coupon is not expired
        if(couponInDb.getG_endDate().isBefore(LocalDate.now())) {
            throw new CustomerException(CustomerErrors.COUPON_DATE_EXPIRED);
        }
        // Add coupon to customer and save to DB
        loggedCustomer = Customer.builder()
                .coupon(coupon)
                .build();
        customerRepo.saveAndFlush(loggedCustomer);
        // Update coupon amount (subtract 1)
        couponInDb.setH_amount(couponInDb.getH_amount()-1);
        couponRepo.save(couponInDb);
        return true;
    }

    @Override
    public List<Coupon> GetCustomerCoupons() {
        return couponRepo.findByCustomerId(this.customerId);
    }

    @Override
    public List<Coupon> GetCustomerCouponsByCategory(Category category) {
        return couponRepo.findByCustomerIdAndCategory(this.customerId, category);
    }

    @Override
    public List<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice) {
        return couponRepo.findByCustomerIdAndPriceLessThan(this.customerId,maxPrice);
    }

    @Override
    public Customer GetCustomerDetails() {
        return this.loggedCustomer;
    }

}
