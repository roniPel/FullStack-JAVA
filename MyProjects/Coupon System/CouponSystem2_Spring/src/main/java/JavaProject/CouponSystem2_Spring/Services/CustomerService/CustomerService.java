package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.*;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;

import java.util.List;

/**
 * Customer Service interface - Lists relevant functionalities for Customer users
 */
public interface CustomerService {

    void SetCustomerId(int customerId);

    /**
     * Adds a coupon purchase in the DB for the logged on customer
     * @param coupon 'coupon' object to purchase
     * @return true if succeeded, false if failed.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    boolean PurchaseCoupon(Coupon coupon) throws CustomerException;

    /**
     * Get all the coupons listed in DB for the customer logged on
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    List<Coupon> GetCustomerCoupons() throws CustomerException;

    /**
     * Get all the coupons listed in DB for the logged on customer belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching category were found.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    List<Coupon> GetCustomerCouponsByCategory(Category category) throws CustomerException;

    /**
     * Get all the coupons listed in DB for the logged on customer up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching max price were found.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    List<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice) throws CustomerException;

    /**
     * Gets a customer (according to the customer ID belonging to the customer logged on)
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    Customer GetCustomerDetails() throws CustomerException;

    /**
     * Get all the coupons listed in DB for a specific customer
     * @return coupon ArrayList if succeeded, null if no coupons were found.
     */
    List<Coupon> GetAllCoupons();

    /**
     * Get a coupon based on provided coupon Id
     * @param couponId id belonging to the coupon requested
     * @return Coupon object if exists
     * @throws CustomerException If we get any exception.  Details are provided
     */
    Coupon GetCouponById(int couponId) throws CustomerException;
    boolean SetCustomerIdByEmail(String email);

    void ClearCustomerId();
}
