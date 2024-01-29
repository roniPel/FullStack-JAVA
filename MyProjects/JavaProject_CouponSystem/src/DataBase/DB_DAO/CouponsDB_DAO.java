package DataBase.DB_DAO;

import Beans.Coupon;
import DataBase.DAO.CouponsDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
import DataBase.SQLcommands;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ErrorHandling.Errors.SQL_ERROR;

public class CouponsDB_DAO implements CouponsDAO {
    private ConnectionPool connectionPool;
    //Todo - finish all class methods

    public static boolean AddCoupon(Coupon coupon) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();

        // Part 1 - Get coupon's categoryID from DB:
        int categoryID;
        params.put(1,coupon.getCategory().toString());
        ResultSet result = DataBase.DButils.runQueryForResult(SQLcommands.getCategoryID, params);
        try {
            categoryID = result.getInt(1);
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        // Part 2 - prepare parameters and create coupon in DB
        params.clear();
        params.put(1,coupon.getCompanyID());
        params.put(2,categoryID);
        params.put(3,coupon.getTitle());
        params.put(4,coupon.getDescription());
        params.put(5,coupon.getStartDate());
        params.put(6,coupon.getEndDate());
        params.put(7,coupon.getAmount());
        params.put(8,coupon.getPrice());
        params.put(9,coupon.getImage());

        if( DButils.runQueryWithMap(SQLcommands.insertCoupon,params)) {
            System.out.println("Coupon was added successfully to DB. ");
            return true;
        }
        else {
            System.out.println("There was a problem adding coupon to DB. ");
            return false;
        }
    }

    public boolean UpdateCoupon(Coupon coupon) {
        return false;
    }

    public boolean DeleteCoupon(int couponID) {
        return false;
    }

    public ArrayList<Coupon> GetAllCoupons() {
        return null;
    }

    public Coupon GetOneCoupon(int couponID) {
        return null;
    }

    public boolean AddCouponPurchase(int customerID, int couponID) {
        return false;
    }

    public boolean DeleteCouponPurchase(int customerID, int couponID) {
        return false;
    }
}
