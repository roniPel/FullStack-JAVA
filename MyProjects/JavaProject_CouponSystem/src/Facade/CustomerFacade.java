package Facade;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;

import java.util.ArrayList;

import static DataBase.DButils.runQueryWithMap;
import static DataBase.DButils.sqlInsertMultiple_IN_Values;

public class CustomerFacade extends ClientFacade{
    //Todo - finish all class methods
    private int customerID; // Customer ID belonging to the customer that logged in

    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean Login(String email, String password) {
        return false;
    }
    public static boolean PurchaseCoupon(Coupon coupon) {
        // Verify coupon doesn't exist for this customer

        // Verify coupon is in stock

        // Verify end date has not passed

        // Buy coupon

        // Update stock count (-1)
        // Todo - use existing update from mockData:
        /*// Part 5 - Update 'amount' column in 'coupons' table
        // Get all coupon IDs from 'customerVsCoupons' table
        ArrayList <Integer> couponIDsForCustomers = GetCouponIDsForCustomers();

        // Prepare multiple IN values SQL String
        String updateCouponsSQL = sqlInsertMultiple_IN_Values(DataBase.CRUD.Update.updateCouponsAmount,couponIDsForCustomers.size());

        // Prepare params map (couponIDs for updating)
        params.clear();
        int counter = 1;
        for(Integer couponID: couponIDsForCustomers){
            params.put(counter++,couponID);
        }
        runQueryWithMap(updateCouponsSQL,params);*/

        return false;
    }

    public static ArrayList<Coupon> GetAllCustomerCoupons() {
        return null;
    }

    public static ArrayList<Coupon> GetCustomerCouponsByCategory(Category category) {
        return null;
    }

    public static ArrayList<Coupon> GetCustomerCouponsByPrice(Double maxPrice) {
        return null;
    }

    public static Customer GetCustomerDetails() {
        return null;
    }
}
