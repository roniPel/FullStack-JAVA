package DataBase.DAO.DB_DAO;

import Beans.Coupon;
import DataBase.DAO.CouponsDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
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

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean AddCoupon(Coupon coupon) throws CouponSystemException {
        // Part 1 - Get coupon's categoryID from DB:
        int categoryID = getCouponCategoryID(coupon.getCategory().toString());
        if(categoryID>0);
        else {
            System.out.println("No category ID found in DB.  Coupon was not added. ");
            return false;
        }
        // Part 2 - prepare parameters and create coupon in DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,coupon.getCompanyID());
        params.put(2,categoryID);
        params.put(3,coupon.getTitle());
        params.put(4,coupon.getDescription());
        params.put(5,coupon.getStartDate());
        params.put(6,coupon.getEndDate());
        params.put(7,coupon.getAmount());
        params.put(8,coupon.getPrice());
        params.put(9,coupon.getImage());

        if( DButils.runQueryWithMap(DataBase.CRUD.Create.insertCoupon,params)) {
            System.out.println("Coupon was added successfully to DB. ");
            return true;
        }
        else {
            System.out.println("There was a problem adding coupon to DB. ");
            return false;
        }
    }

    /**
     * Provides coupon's category ID from DB - based on the details listed in the param
     * @param categoryName a String containing category name to search for in DB
     * @return int with category ID if succeeded, -1 if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static int getCouponCategoryID(String categoryName) throws CouponSystemException {
        int categoryID;
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,categoryName);
        ResultSet result = DataBase.DButils.runQueryForResult(DataBase.CRUD.Read.getCategoryID, params);
        try {
            while(result.next()) {
                categoryID = result.getInt(1);
                return categoryID;
            }
            return -1;
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
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
