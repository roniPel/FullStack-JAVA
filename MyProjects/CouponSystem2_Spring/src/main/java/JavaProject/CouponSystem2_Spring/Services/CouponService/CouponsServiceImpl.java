package JavaProject.CouponSystem2_Spring.Services.CouponService;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class CouponsServiceImpl implements CouponsService{
    @Autowired
    private CouponRepository couponRepo;
    //Todo - write all methods
    @Override
    public boolean AddCoupon(Coupon coupon) throws AdminException, CompanyException {
        return false;
    }

    @Override
    public int GetCouponIDByTitle(String title) throws AdminException, CustomerException {
        return 0;
    }

    @Override
    public boolean UpdateCoupon(Coupon coupon) throws CompanyException, CustomerException {
        return false;
    }

    @Override
    public boolean DeleteCoupon(int couponID) throws AdminException, CompanyException {
        return false;
    }

    @Override
    public ArrayList<Coupon> GetAllCoupons() throws CustomerException {
        return null;
    }

    @Override
    public Coupon GetOneCoupon(int couponID) throws CompanyException, CustomerException {
        return null;
    }

    @Override
    public ArrayList<Coupon> GetCouponsForCustomer(int customerID) throws CustomerException {
        return null;
    }

    @Override
    public boolean AddCouponPurchase(int customerID, int couponID) throws CustomerException, AdminException {
        return false;
    }

    @Override
    public boolean DeleteCouponPurchase(int customerID, int couponID) throws CustomerException {
        return false;
    }

    @Override
    public Map<Integer, String> GetAllCategories() throws AdminException, CompanyException, CustomerException {
        return null;
    }

    @Override
    public ResultSet GetExpiredCoupons() throws AdminException {
        return null;
    }

    @Override
    public ArrayList<Coupon> AddResultsToCouponList(ResultSet results) throws AdminException {
        return null;
    }

    @Override
    public ArrayList<Coupon> GetCompanyCouponsByCategoryId(int categoryId, int companyId) throws CompanyException {
        return null;
    }

    @Override
    public ArrayList<Coupon> GetCompanyCouponsByMaxPrice(double maxPrice, int companyId) throws CompanyException {
        return null;
    }

    @Override
    public ArrayList<Coupon> GetCustomerCouponsByCategoryId(int categoryId, int customerId) throws CustomerException {
        return null;
    }

    @Override
    public ArrayList<Coupon> GetCustomerCouponsByMaxPrice(double maxPrice, int customerId) throws CustomerException {
        return null;
    }
}
