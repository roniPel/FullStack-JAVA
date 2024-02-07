package Facade;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;
import DataBase.DAO.CompaniesDAO;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.CustomersDAO;
import DataBase.DAO.DB_DAO.CompaniesDB_DAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;

import java.util.ArrayList;

public class CustomerFacade extends ClientFacade{

    private final CompaniesDAO companiesDAO = new CompaniesDB_DAO();
    private final CustomersDAO customersDAO = new CustomersDB_DAO();
    private final CouponsDAO couponsDAO = new CouponsDB_DAO();
    //Todo - finish all class methods
    private int customerID; // Customer ID belonging to the customer that logged in


    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean Login(String email, String password) {
        return false;
    }
    public boolean PurchaseCoupon(Coupon coupon) {
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

    public ArrayList<Coupon> GetAllCustomerCoupons() {
        return null;
    }

    public ArrayList<Coupon> GetCustomerCouponsByCategory(Category category) {
        return null;
    }

    public ArrayList<Coupon> GetCustomerCouponsByPrice(Double maxPrice) {
        return null;
    }

    public Customer GetCustomerDetails() {
        return null;
    }
}
