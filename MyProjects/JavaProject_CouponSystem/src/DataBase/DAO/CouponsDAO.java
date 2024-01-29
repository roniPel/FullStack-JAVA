package DataBase.DAO;

import Beans.Coupon;

import java.util.ArrayList;

public interface CouponsDAO {
    static boolean AddCoupon(Coupon coupon){return false;};
    static boolean UpdateCoupon(Coupon coupon){return false;};
    static boolean DeleteCoupon(int couponID){return false;};
    static ArrayList<Coupon> GetAllCoupons(){return null;};
    static Coupon GetOneCoupon(int couponID){return null;};
    static boolean AddCouponPurchase(int customerID, int couponID){return false;};
    static boolean DeleteCouponPurchase(int customerID, int couponID){return false;};
}
