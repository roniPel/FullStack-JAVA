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
    static boolean AddCoupon(Coupon coupon) throws CouponSystemException {return false;};


    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean UpdateCoupon(Coupon coupon) throws CouponSystemException {return false;};


    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param couponID the ID of the coupon to be deleted in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean DeleteCoupon(int couponID) throws CouponSystemException {return false;};


    /**
     * Get all the coupons listed in DB
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static ArrayList<Coupon> GetAllCoupons() throws CouponSystemException {return null;};


    /**
     * Get one Coupon in DB - based on the details listed in the param
     * @param couponID the coupon's ID to get from the DB
     * @return 'Coupon' object if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static Coupon GetOneCoupon(int couponID) throws CouponSystemException {return null;};


    /**
     * Add a Coupon purchase in DB - based on the details listed in the params
     * @param customerID the customer ID of the customer that wants to buy the coupon
     * @param couponID the coupon ID of the coupon the customer wants to buy
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean AddCouponPurchase(int customerID, int couponID) throws CouponSystemException {return false;};


    /**
     * Delete Coupon purchase in DB - based on the details listed in the param
     * @param customerID the customer ID of the customer that wants to delete the coupon
     * @param couponID the coupon ID of the coupon the customer wants to delete its purchase
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean DeleteCouponPurchase(int customerID, int couponID) throws CouponSystemException {return false;};
}
