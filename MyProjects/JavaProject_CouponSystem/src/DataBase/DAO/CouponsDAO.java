package DataBase.DAO;

import Beans.Coupon;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;

public interface CouponsDAO {

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    boolean AddCoupon(Coupon coupon) throws CouponSystemException;

    /**
     * Returns a coupon's ID based on title (unique)
     * @param title coupon's title
     * @return couponID if coupon exists, -1 if coupon doesn't exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    int GetCouponIDByTitle(String title) throws CouponSystemException;

    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    boolean UpdateCoupon(Coupon coupon) throws CouponSystemException;


    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param couponID the ID of the coupon to be deleted in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    boolean DeleteCoupon(int couponID) throws CouponSystemException;


    /**
     * Get all the coupons listed in DB
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> GetAllCoupons() throws CouponSystemException;


    /**
     * Get one Coupon in DB - based on the details listed in the param
     * @param couponID the coupon's ID to get from the DB
     * @return 'Coupon' object if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    Coupon GetOneCoupon(int couponID) throws CouponSystemException;


    /**
     * Get all the coupons listed in DB for a specific customer
     * @param customerID ID belonging to the customer the coupons belong to
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> GetCouponsForCustomer(int customerID) throws CouponSystemException;


    /**
     * Add a Coupon purchase in DB - based on the details listed in the params
     * @param customerID the customer ID of the customer that wants to buy the coupon
     * @param couponID the coupon ID of the coupon the customer wants to buy
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    boolean AddCouponPurchase(int customerID, int couponID) throws CouponSystemException;


    /**
     * Delete Coupon purchase in DB - based on the details listed in the param
     * @param customerID the customer ID of the customer that wants to delete the coupon
     * @param couponID the coupon ID of the coupon the customer wants to delete its purchase
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    boolean DeleteCouponPurchase(int customerID, int couponID) throws CouponSystemException;
}
