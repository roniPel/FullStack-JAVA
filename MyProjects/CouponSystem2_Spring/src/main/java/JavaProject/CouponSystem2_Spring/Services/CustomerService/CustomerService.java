package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void PurchaseCoupon() throws CustomerException;

    /**
     * Customer Method - Get Customer Coupons
     * @throws CustomerException If we get any exception.  Details are provided
     */
    public void GetCustomerCoupons() throws CustomerException;

    /**
     * Customer Method - Get Customer Coupons by Category
     * @throws CustomerException  If we get any exception.  Details are provided
     */
    public void GetCustomerCouponsByCategory() throws CustomerException;

    /**
     * Customer Method - Get Customer Coupons by max price
     * @throws CustomerException If we get any exception.  Details are provided
     */
    public void GetCustomerCouponsByMaxPrice() throws CustomerException;

    /**
     * Customer Method - Get Customer Details
     * @throws CustomerException If we get any exception.  Details are provided
     */
    public void GetCustomerDetails() throws CustomerException;
}
