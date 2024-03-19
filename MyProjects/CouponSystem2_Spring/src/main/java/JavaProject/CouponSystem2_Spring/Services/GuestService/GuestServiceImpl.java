package JavaProject.CouponSystem2_Spring.Services.GuestService;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Guest Service Implementation for Coupon System 2
 */
@Service
@RequiredArgsConstructor
@Getter
public class GuestServiceImpl implements GuestService{

    @Autowired
    CouponRepository couponRepo;
    @Autowired
    CustomerRepository customerRepo;

    @Getter
    private int guestId;  // Guest ID belonging to the customer that logged in
    //Todo - finish writing guest service Impl methods
    @Override
    public boolean Login(String email, String password) throws CustomerException, AdminException, CompanyException {
        Customer customer = customerRepo.findByEmailAndPassword(email,password);
        if(customer == null) {
            throw new CustomerException(CustomerErrors.INCORRECT_LOGIN_DETAILS);
        }
        else {
            this.guestId = customer.getId();
            return true;
        }
    }

    @Override
    public boolean PurchaseCoupon(Coupon coupon) throws GuestException {
        // Verify coupon exists in DB
        Coupon couponInDb = couponRepo.findById(coupon.getId()).orElseThrow(
                () -> new GuestException(GuestErrors.COUPON_DOES_NOT_EXIST)
        );
        // Verify amount is above 0
        if(couponInDb.getAmount() <= 0){
            throw new GuestException(GuestErrors.COUPON_AMOUNT_IS_ZERO);
        }
        // Verify coupon is not expired
        if(couponInDb.getEnd_date().isBefore(LocalDate.now())) {
            throw new GuestException(GuestErrors.COUPON_DATE_EXPIRED);
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
    public List<Coupon> GetAllCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public Coupon GetCouponById(int couponId) throws GuestException {
        return couponRepo.findById(couponId).orElseThrow(
                () ->new GuestException(GuestErrors.COUPON_DOES_NOT_EXIST));
    }

    @Override
    public Customer GetCustomerDetails() throws GuestException {
        return customerRepo.findById(this.guestId).orElseThrow(
                () -> new GuestException(GuestErrors.GENERAL_CUSTOMER_ERROR) );
    }

}
