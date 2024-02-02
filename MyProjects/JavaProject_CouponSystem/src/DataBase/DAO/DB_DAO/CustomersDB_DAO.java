package DataBase.DAO.DB_DAO;

import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DataBase.DAO.CustomersDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ErrorHandling.Errors.SQL_ERROR;

public class CustomersDB_DAO implements CustomersDAO {
    private ConnectionPool connectionPool;
    //Todo - finish all class methods

    /**
     * Checks whether a customer exists in the DB
     * @param email - customer's email
     * @param password - customer's password
     * @return - true if customer exists, false if customer doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    public boolean IsCustomerExists(String email, String password) throws CouponSystemException {
        //Todo - finish
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,email);
        params.put(2,password);
        ResultSet result = DataBase.DButils.runQueryForResult(DataBase.CRUD.Read.companyExists, params);

        return false;
    }

    public boolean AddCustomer(Customer customer) {
        return false;
    }

    public boolean UpdateCustomer(Customer customer) {
        return false;
    }

    public boolean deleteCustomer(int customerID) {
        return false;
    }


    /**
     * Gets an ArrayList of all the customers listed in the DB
     * @return an ArrayList of 'Customer' class items if succeeded, 'null' if failed or if no companies exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Customer> GetAllCustomers() throws CouponSystemException {
        // Part 1 - Get customers - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(DataBase.CRUD.Read.getAllCustomers,params);

        // Part 2 - add results to customer list
        ArrayList<Customer> customerList = new ArrayList<>();

        try {
            while (results.next()) {
                int id = results.getInt(1);
                String firstName = results.getString(2);
                String lastName = results.getString(3);
                String email = results.getString(4);
                String password = results.getString(5);

                // Part 2 - Get coupons for customer - query from DB
                ArrayList<Coupon> coupons = CouponsDB_DAO.GetCouponsForCustomer(id);

                // Create a new Customer object in the customerList
                customerList.add(new Customer(id, firstName, lastName, email, password, coupons));
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return customerList;
    }

    public Customer GetOneCustomer(int customerID) {
        return null;
    }
}
