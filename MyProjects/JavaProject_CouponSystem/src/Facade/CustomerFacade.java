package Facade;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;

import java.util.ArrayList;

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