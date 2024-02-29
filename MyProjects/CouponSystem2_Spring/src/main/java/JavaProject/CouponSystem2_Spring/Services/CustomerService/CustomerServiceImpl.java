package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    protected CouponRepository couponRepo;
    @Autowired
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
            this.customerId = customer.getId();
            return true;
        }
    }

    @Override
    public boolean PurchaseCoupon(Coupon coupon) throws CustomerException {
        // Verify coupon exists in DB
        Coupon couponInDb = couponRepo.findById(coupon.getId()).orElseThrow(
                () -> new CustomerException(CustomerErrors.COUPON_DOES_NOT_EXIST)
        );
        // Verify coupon exists for customer
        if(customerRepo.existsById(coupon.getId())){
            throw new CustomerException(CustomerErrors.COUPON_EXISTS_FOR_CUSTOMER);
        }
        // Verify amount is above 0
        if(couponInDb.getAmount() <= 0){
            throw new CustomerException(CustomerErrors.COUPON_AMOUNT_IS_ZERO);
        }
        // Verify coupon is not expired
        if(couponInDb.getEnd_date().isBefore(LocalDate.now())) {
            throw new CustomerException(CustomerErrors.COUPON_DATE_EXPIRED);
        }
        // Add coupon to customer and save to DB
        loggedCustomer = Customer.builder()
                .coupon(coupon)
                .build();
        customerRepo.saveAndFlush(loggedCustomer);
        // Update coupon amount (subtract 1)
        couponInDb.setAmount(couponInDb.getAmount()-1);
        couponRepo.save(couponInDb);
        return true;
    }

    @Override
    public List<Coupon> GetCustomerCoupons() {
        //Todo - uncomment
        //return couponRepo.findByCustomerId(this.customerId);
        return null;
    }

    @Override
    public List<Coupon> GetCustomerCouponsByCategory(Category category) {
        //Todo - uncomment
        //return couponRepo.findByCustomerIdAndCategory(this.customerId, category);
        return null;
    }

    @Override
    public List<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice) {
        //Todo - uncomment
        //return couponRepo.findByCustomerIdAndPriceLessThanEqual(this.customerId,maxPrice);
        return null;
    }

    @Override
    public Customer GetCustomerDetails() {
        return this.loggedCustomer;
    }

}
