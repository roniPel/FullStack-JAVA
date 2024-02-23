package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;

public interface CompanyService extends ClientService {

    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException;

    /**
     * Company Method - Add Coupon
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    void AddCoupon() throws CompanyException;

    /**
     * Company Method - Update Coupon
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    void UpdateCoupon() throws CompanyException;

    /**
     * Company Method - Delete Coupon
     * @throws CompanyException If we get any exception.  Details are provided
     */
    void DeleteCoupon() throws CompanyException;

    /**
     * Company Method - Get company Coupons
     * @throws CompanyException If we get any exception.  Details are provided
     */
    void GetCompanyCoupons() throws CompanyException;

    /**
     * Company Method - Get Company Coupons by Category
     * @throws CompanyException If we get any exception.  Details are provided
     */
    void GetCompanyCouponsByCategory() throws CompanyException;

    /**
     * Company Method - Get Company Coupons by max price
     * @throws CompanyException If we get any exception.  Details are provided
     */
    void GetCompanyCouponsByMaxPrice() throws CompanyException;

    /**
     * Company Method - Get Company Details
     * @throws CompanyException If we get any exception.  Details are provided
     */
    void GetCompanyDetails() throws CompanyException;
}
