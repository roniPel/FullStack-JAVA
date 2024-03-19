package JavaProject.CouponSystem2_Spring.Services.GuestService;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;

import java.util.List;

/**
 * Guest Service interface - Lists relevant functionalities for Guest users
 */
public interface GuestService extends ClientService {
    @Override
    boolean Login(String email, String password) throws CustomerException, AdminException, CompanyException;
    /**
     * Get all the coupons listed in DB for a specific customer
     * @return coupon ArrayList if succeeded, null if no coupons were found.
     */
    List<Coupon> GetAllCoupons();

    /**
     * Get a coupon based on provided coupon Id
     * @param couponId id belonging to the coupon requested
     * @return Coupon object if exists
     * @throws GuestException If we get any exception.  Details are provided
     */
    Coupon GetCouponById(int couponId) throws GuestException;

    /**
     * Adds a coupon purchase in the DB for the logged on customer
     *
     * @param coupon 'coupon' object to purchase
     * @return true if succeeded, false if failed.
     * @throws GuestException If we get any exception.  Details are provided
     */
    boolean PurchaseCoupon(Coupon coupon) throws GuestException;

    /**
     * Gets a customer (according to the customer ID belonging to the customer logged on)
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws GuestException If we get any exception.  Details are provided
     */
    Customer GetCustomerDetails() throws GuestException;

}
