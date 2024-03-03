package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
public interface CustomerService extends ClientService {

    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @Override
    public boolean Login(String email, String password) throws CustomerException,AdminException, CompanyException;

    /**
     * Customer Method - Purchase Coupon
     * @throws CustomerException If we get any exception.  Details are provided
     */
    boolean PurchaseCoupon(Coupon coupon) throws CustomerException;

    /**
     * Customer Method - Get Customer Coupons
     */
    List<Coupon> GetCustomerCoupons() ;

    /**
     * Customer Method - Get Customer Coupons by Category
     */
    List<Coupon> GetCustomerCouponsByCategory(Category category) ;

    /**
     * Customer Method - Get Customer Coupons by max price
     */
    List<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice) ;

    /**
     * Customer Method - Get Customer Details
     */
    Customer GetCustomerDetails() throws CustomerException;

    List<Coupon> GetAllCoupons();
}
