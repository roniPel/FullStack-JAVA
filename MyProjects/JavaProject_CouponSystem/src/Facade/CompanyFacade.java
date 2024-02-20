package Facade;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DataBase.DAO.CompaniesDAO;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.CustomersDAO;
import DataBase.DAO.DB_DAO.CompaniesDB_DAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Company Facade - for running admin methods
 */
public class CompanyFacade extends ClientFacade{

    private final CompaniesDAO companiesDAO = new CompaniesDB_DAO();

    private final CouponsDAO couponsDAO = new CouponsDB_DAO();
    private int companyID;  // Company ID belonging to the company that logged in

    /**
     * Constructor
     * @param companyID company ID belonging to company logged on
     */
    public CompanyFacade(int companyID) {
        this.companyID = companyID;
    }


    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    @Override
    public boolean Login(String email, String password) throws CouponSystemException {
        if(companiesDAO.IsCompanyExists(email,password) ) {
            this.companyID = companiesDAO.GetCompanyIDByEmail(email);
            return true;
        }
        return false;
    }


    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean AddCoupon(Coupon coupon) throws CouponSystemException {
        // Can't add a coupon with same Title as coupon belonging to this company (based on this.companyID)
        // Part 1 - Check there is no coupon with same title listed on companyID
        ArrayList<Coupon> coupons = companiesDAO.GetOneCompany(this.companyID).getCoupons();
        if(coupons == null);
        else {
            for (Coupon coup : coupons) {
                if (coup.getTitle().equals(coupon.getTitle())) {
                    throw new CouponSystemException(Errors.COUPON_EXISTS_FOR_COMPANY);
                }
            }
        }
        // Part 2 - verify companyID listed in coupon matches logged on company
        if(coupon.getCompanyID() == this.companyID) {
            // Part 3 - add coupon to company
            return couponsDAO.AddCoupon(coupon);
        }
        else {
            throw new CouponSystemException(Errors.COUPON_COMPANY_ID_INCORRECT);
        }
    }


    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean UpdateCoupon(Coupon coupon) throws CouponSystemException {
        // Verify can't update company ID - option not available in DB
        // Verify can't update coupon ID - option not available in DB
        // Part 1 - Verify coupon is linked to the company logged on (company ID)
        if(coupon.getCompanyID() == this.companyID) {
            // Part 2 - add coupon to company
            return couponsDAO.UpdateCoupon(coupon);
        }
        else {
            throw new CouponSystemException(Errors.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
    }


    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param couponID the ID of the coupon to be deleted in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean DeleteCoupon(int couponID) throws CouponSystemException {
        // Delete linked coupon purchases - performed by DB with cascade config.
        // Part 1 - Verify coupon exists in DB
        Coupon coupon = couponsDAO.GetOneCoupon(couponID);
        if(coupon == null) {
            throw new CouponSystemException(Errors.COUPON_DOES_NOT_EXIST);
        }
        // Part 2 - Verify coupon is linked to the company logged on (company ID)
        if(coupon.getCompanyID() == this.companyID) {
            // Part 3 - Delete coupon from DB
            return couponsDAO.DeleteCoupon(couponID);
        }
        else {
            throw new CouponSystemException(Errors.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
    }


    /**
     * Get all the coupons listed in DB for a specific company
     * @return coupon ArrayList if succeeded, null if no coupons were found.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public ArrayList<Coupon> GetAllCompanyCoupons() throws CouponSystemException {
        return companiesDAO.GetOneCompany(this.companyID).getCoupons();
    }


    /**
     * Get all the coupons listed in DB for the logged on company belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupon ArrayList if succeeded, null if no coupons matching category were found.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public ArrayList<Coupon> GetCompanyCouponsByCategory(Category category) throws CouponSystemException {
        // Part 1 - find relevant category Id
        Map<Integer,String> categories = couponsDAO.GetAllCategories();
        Stream<Integer> categoryIdStream = categories
                .entrySet()
                .stream()
                .filter(categoryName -> category.toString().equals(categoryName.getValue()))
                .map(Map.Entry::getKey);
        int categoryId = categoryIdStream.findAny().orElse(-1);

        // Part 2 - Send query to DB for coupons by category
        return couponsDAO.GetCompanyCouponsByCategoryId(categoryId, this.companyID);
    }

    /**
     * Get all the coupons listed in DB for the logged on company up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupon ArrayList if succeeded, null if no coupons matching max price were found.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public ArrayList<Coupon> GetCompanyCouponsByPrice(Double maxPrice) throws CouponSystemException {
        return couponsDAO.GetCompanyCouponsByMaxPrice(maxPrice,this.companyID);
    }

    /**
     * Gets a company (according to the company ID belonging to the company logged on)
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public Company GetCompanyDetails() throws CouponSystemException {
        return companiesDAO.GetOneCompany(this.companyID);
    }
}
