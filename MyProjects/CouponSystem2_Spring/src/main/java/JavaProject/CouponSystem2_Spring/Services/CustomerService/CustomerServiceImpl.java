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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Customer Service Implementation for Coupon System 2
 */
@Service
@RequiredArgsConstructor
@Getter
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CouponRepository couponRepo;
    @Autowired
    CustomerRepository customerRepo;

    @Getter
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
        // Verify coupon does NOT already exist for customer
        List<Coupon> customerCoupons = GetCustomerCoupons();
        if(customerCoupons.contains(coupon)){
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
        Customer customer = GetCustomerDetails();
        customer.getCoupons().add(coupon);
        customerRepo.saveAndFlush(customer);
        // Update coupon amount (subtract 1)
        couponInDb.setAmount(couponInDb.getAmount()-1);
        couponRepo.save(couponInDb);
        return true;
    }

    @Override
    public List<Coupon> GetCustomerCoupons() {
        return couponRepo.findByCustomers_id(this.customerId);
    }

    @Override
    public List<Coupon> GetCustomerCouponsByCategory(Category category) {
        return couponRepo.findByCategoryAndCustomers_id(category, this.customerId);
    }

    @Override
    public List<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice) {
        return couponRepo.findByPriceLessThanEqualAndCustomers_id(maxPrice, this.customerId);
    }

    @Override
    public Customer GetCustomerDetails() throws CustomerException {
        return customerRepo.findById(this.customerId).orElseThrow(
                () -> new CustomerException(CustomerErrors.CUSTOMER_DOES_NOT_EXIST) );
    }

    @Override
    public List<Coupon> GetAllCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public Coupon GetCouponById(int couponId) throws CustomerException {
        return couponRepo.findById(couponId).orElseThrow(
                () ->new CustomerException(CustomerErrors.COUPON_DOES_NOT_EXIST));
    }


}
