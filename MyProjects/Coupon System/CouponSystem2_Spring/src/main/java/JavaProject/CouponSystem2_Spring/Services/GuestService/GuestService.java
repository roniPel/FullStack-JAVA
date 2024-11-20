package JavaProject.CouponSystem2_Spring.Services.GuestService;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;

import java.util.List;

/**
 * Guest Service interface - Lists relevant functionalities for Guest users
 */
public interface GuestService {
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

    int AddCustomer(Customer customer) throws GuestException;

}
