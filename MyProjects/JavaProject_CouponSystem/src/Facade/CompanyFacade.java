package Facade;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;

import java.util.ArrayList;

public class CompanyFacade extends ClientFacade{
    //Todo - finish all class methods
    private int companyID;  // Company ID belonging to the company that logged in

    public CompanyFacade(int companyID) {
        this.companyID = companyID;
    }

    @Override
    public boolean Login(String email, String password) {
        return false;
    }
    public boolean AddCoupon(Coupon coupon) {
        return false;
    }

    public boolean UpdateCoupon(Coupon coupon) {
        return false;
    }

    public static boolean DeleteCoupon(int couponID) {
        return false;
    }

    public ArrayList<Coupon> GetAllCompanyCoupons() {
        return null;
    }

    public ArrayList<Coupon> GetCompanyCouponsByCategory(Category category) {
        return null;
    }
    public ArrayList<Coupon> GetCompanyCouponsByPrice(Double maxPrice) {
        return null;
    }

    public Company GetCompanyDetails() {
        return null;
    }
}
