package Facade;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;
import DataBase.DAO.CompaniesDAO;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.CustomersDAO;
import DataBase.DAO.DB_DAO.CompaniesDB_DAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;

import java.sql.Array;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Customer Facade - for running admin methods
 */
public class CustomerFacade extends ClientFacade{

    private final CustomersDAO customersDAO = new CustomersDB_DAO();
    private final CouponsDAO couponsDAO = new CouponsDB_DAO();

    //Todo - test facade


    private int customerID; // Customer ID belonging to the customer that logged in


    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    @Override
    public boolean Login(String email, String password) throws CouponSystemException {
        if(customersDAO.IsCustomerExists(email,password) ) {
            this.customerID = customersDAO.GetCustomerIDByEmail(email);
            return true;
        }
        return false;
    }


    /**
     * Adds a coupon purchase in the DB for the logged on customer
     * @param coupon 'coupon' object to purchase
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean PurchaseCoupon(Coupon coupon) throws CouponSystemException {

        // Part 1 - Verify coupon exists in DB
        int couponDbId = couponsDAO.GetCouponIDByTitle(coupon.getTitle());
        Coupon couponForPurchase;
        if(couponDbId < 0) {
            throw new CouponSystemException(Errors.COUPON_DOES_NOT_EXIST);
        }
        else {
            // Save the requested coupon details locally - for use in upcoming parts
            couponForPurchase = couponsDAO.GetOneCoupon(couponDbId);
        }

        // Part 2 - Verify the requested coupon doesn't exist for this customer
        ArrayList<Coupon> couponsForCustomer = GetAllCustomerCoupons();
        if(couponsForCustomer == null) {}
        else {
            for (Coupon customerCoupon : couponsForCustomer) {
                if (customerCoupon.getId() == coupon.getId()) {
                    throw new CouponSystemException(Errors.COUPON_EXISTS_FOR_CUSTOMER);
                }
            }
        }

        // Part 3 - Verify amount is more than 0
        if(couponForPurchase.getAmount() <= 0) {
            throw new CouponSystemException(Errors.COUPON_AMOUNT_IS_ZERO);
        }

        // Part 4 - Verify end date has not passed
        LocalDate today = LocalDate.now();
        if( today.isAfter(couponForPurchase.getEndDate()) ) {
            throw new CouponSystemException(Errors.COUPON_DATE_EXPIRED);
        }

        // Part 5 - Buy coupon
        couponsDAO.AddCouponPurchase(this.customerID,couponDbId);

        // Part 6 - Update stock count (-1)
        int amount = couponForPurchase.getAmount();
        couponForPurchase.setAmount(amount-1);
        couponsDAO.UpdateCoupon(couponForPurchase);

        return true;
    }


    /**
     * Get all the coupons listed in DB for the customer logged on
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> GetAllCustomerCoupons() throws CouponSystemException {
        return couponsDAO.GetCouponsForCustomer(this.customerID);

    }


    /**
     * Get all the coupons listed in DB for the logged on customer belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching category were found.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> GetCustomerCouponsByCategory(Category category) throws CouponSystemException {
        // Part 1 - get all customer coupons
        ArrayList<Coupon> coupons = GetAllCustomerCoupons();
        // Part 2 - iterate over customer coupons and add relevant coupons to couponsByCategory list
        ArrayList<Coupon> couponsByCategory = new ArrayList<>();
        for(Coupon coupon: coupons) {
            if(coupon.getCategory().equals(category)) {
                couponsByCategory.add(coupon);
            }
        }
        return couponsByCategory;
    }


    /**
     * Get all the coupons listed in DB for the logged on customer up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching max price were found.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> GetCustomerCouponsByPrice(Double maxPrice) throws CouponSystemException {
        // Part 1 - get all customer coupons
        ArrayList<Coupon> coupons = GetAllCustomerCoupons();
        // Part 2 - iterate over customer coupons and add relevant coupons to couponsByMaxPrice list
        ArrayList<Coupon> couponsByMaxPrice = new ArrayList<>();
        for(Coupon coupon: coupons) {
            if(coupon.getPrice() <= maxPrice) {
                couponsByMaxPrice.add(coupon);
            }
        }
        return couponsByMaxPrice;
    }


    /**
     * Gets a customer (according to the customer ID belonging to the customer logged on)
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Customer GetCustomerDetails() throws CouponSystemException {
        return customersDAO.GetOneCustomer(this.customerID);
    }

    public ArrayList<Coupon> GetAllCoupons() throws CouponSystemException {
        return couponsDAO.GetAllCoupons();
    }

}
