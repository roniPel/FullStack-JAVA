package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import java.util.List;

/**
 * Company Service interface - Lists relevant functionalities for Company users
 */
public interface CompanyService extends ClientService {

    @Override
    boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException;

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean AddCoupon(Coupon coupon) throws CompanyException;

    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @return true if succeeded, false if failed.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean UpdateCoupon(Coupon coupon) throws CompanyException;

    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param couponId the ID of the coupon to be deleted in the DB
     * @return true if succeeded, false if failed.
     * @throws CompanyException If we get any exception.  Details are provided
     */
    boolean DeleteCoupon(int couponId) throws CompanyException;

    /**
     * Get all the coupons listed in DB for a specific company
     * @return coupon List if succeeded, null if no coupons were found.
     */
    List<Coupon> GetCompanyCoupons();

    /**
     * Get all the coupons listed in DB for the logged on company belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupon List if succeeded, null if no coupons matching category were found.
     */
    List<Coupon> GetCompanyCouponsByCategory(Category category);

    /**
     * Get all the coupons listed in DB for the logged on company up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupon List if succeeded, null if no coupons matching max price were found.
     */
    List<Coupon> GetCompanyCouponsByMaxPrice(Double maxPrice);

    /**
     * Gets a company (according to the company ID belonging to the company logged on)
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CompanyException If we get any exception.  Details are provided
     */
    Company GetCompanyDetails() throws CompanyException;

    /**
     * Get one coupon
     * @param id id belonging to the coupon requested
     * @return coupon object with the requested coupon details
     * @throws CompanyException If we get any exception.  Details are provided
     */
    Coupon GetOneCoupon(int id) throws CompanyException;

    /**
     * Get one company
     * @param id id belonging to the company requested
     * @return company object with the requested company details
     * @throws CompanyException If we get any exception.  Details are provided
     */
    Company GetOneCompany(int id) throws CompanyException;
}
