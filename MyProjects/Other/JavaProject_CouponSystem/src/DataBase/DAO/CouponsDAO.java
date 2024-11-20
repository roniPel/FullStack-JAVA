package DataBase.DAO;

import Beans.Coupon;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Interface used for creating coupons DB DAO class
 */
public interface CouponsDAO {

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean AddCoupon(Coupon coupon) throws CouponSystemException;

    /**
     * Returns a coupon's ID based on title (unique)
     * @param title coupon's title
     * @return couponID if coupon exists, -1 if coupon doesn't exist.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    int GetCouponIDByTitle(String title) throws CouponSystemException;

    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean UpdateCoupon(Coupon coupon) throws CouponSystemException;

    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param couponID the ID of the coupon to be deleted in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean DeleteCoupon(int couponID) throws CouponSystemException;

    /**
     * Get all the coupons listed in DB
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    ArrayList<Coupon> GetAllCoupons() throws CouponSystemException;


    /**
     * Get one Coupon in DB - based on the details listed in the param
     * @param couponID the coupon's ID to get from the DB
     * @return 'Coupon' object if succeeded, null if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    Coupon GetOneCoupon(int couponID) throws CouponSystemException;


    /**
     * Get all the coupons listed in DB for a specific customer
     * @param customerID ID belonging to the customer the coupons belong to
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    ArrayList<Coupon> GetCouponsForCustomer(int customerID) throws CouponSystemException;


    /**
     * Add a Coupon purchase in DB - based on the details listed in the params
     * @param customerID the customer ID of the customer that wants to buy the coupon
     * @param couponID the coupon ID of the coupon the customer wants to buy
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean AddCouponPurchase(int customerID, int couponID) throws CouponSystemException;


    /**
     * Delete Coupon purchase in DB - based on the details listed in the param
     * @param customerID the customer ID of the customer that wants to delete the coupon
     * @param couponID the coupon ID of the coupon the customer wants to delete its purchase
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean DeleteCouponPurchase(int customerID, int couponID) throws CouponSystemException;

    /**
     * Gets a map of all the categories listed in the DB
     * @return a map of categoryID (Integer) and name (String) if succeeded, 'null' if failed or if no categories exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    Map<Integer, String> GetAllCategories() throws CouponSystemException;

    /**
     * Sends a query to DB and returns a list of expired coupons for deletion
     * @return ResultSet containing a list of expired coupons (to be deleted)
     */
    ResultSet GetExpiredCoupons() throws CouponSystemException;

    /**
     * Converts a result set from SQL DB to an Array list of coupon objects
     * @param results result set containing all Coupons from DB
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> AddResultsToCouponList(ResultSet results) throws CouponSystemException;

    /**
     * Sends a query to the DB for coupons from a specific company and specific category Id - specified in params
     * @param categoryId Category id for the coupons
     * @param companyId Company id for the coupons
     * @return An array list of coupons that answer the specifications
     * @throws CouponSystemException  If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> GetCompanyCouponsByCategoryId(int categoryId, int companyId) throws CouponSystemException;

    /**
     * Sends a query to the DB for coupons from a specific company and under a maximum price - specified in params
     * @param maxPrice Max price for the coupons
     * @param companyId Company id for the coupons
     * @return An array list of coupons that answer the specifications
     * @throws CouponSystemException  If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> GetCompanyCouponsByMaxPrice(double maxPrice, int companyId) throws CouponSystemException;

    /**
     * Sends a query to the DB for coupons from a specific customer and specific category Id - specified in params
     * @param categoryId Category id for the coupons
     * @param customerId Customer id for the coupons
     * @return An array list of coupons that answer the specifications
     * @throws CouponSystemException  If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> GetCustomerCouponsByCategoryId(int categoryId, int customerId) throws CouponSystemException;

    /**
     * Sends a query to the DB for coupons from a specific customer and under a maximum price - specified in params
     * @param maxPrice Max price for the coupons
     * @param customerId Customer id for the coupons
     * @return An array list of coupons that answer the specifications
     * @throws CouponSystemException  If we get any SQL exception.  Details are provided
     */
    ArrayList<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice, int customerId) throws CouponSystemException;
}
