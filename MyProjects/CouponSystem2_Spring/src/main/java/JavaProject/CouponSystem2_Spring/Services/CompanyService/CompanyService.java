package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    boolean AddCoupon(Coupon coupon) throws CompanyException;

    /**
     * Company Method - Update Coupon
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean UpdateCoupon(Coupon coupon) throws CompanyException;

    /**
     * Company Method - Delete Coupon
     * @throws CompanyException If we get any exception.  Details are provided
     */
    boolean DeleteCoupon(int couponId) throws CompanyException;

    /**
     * Company Method - Get company Coupons
     */
    List<Coupon> GetCompanyCoupons();

    /**
     * Company Method - Get Company Coupons by Category
     */
    List<Coupon> GetCompanyCouponsByCategory(Category category);

    /**
     * Company Method - Get Company Coupons by max price
     */
    List<Coupon> GetCompanyCouponsByMaxPrice(Double maxPrice);

    /**
     * Company Method - Get Company Details
     * @throws CompanyException If we get any exception.  Details are provided
     */
    Company GetCompanyDetails() throws CompanyException;

    int AddCompanyWithFullCoupons();
}
