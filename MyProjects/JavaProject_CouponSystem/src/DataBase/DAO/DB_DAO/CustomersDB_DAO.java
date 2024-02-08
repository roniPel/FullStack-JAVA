package DataBase.DAO.DB_DAO;

import Beans.Coupon;
import Beans.Customer;
import DataBase.CRUD.Delete;
import DataBase.CRUD.Read;
import DataBase.DAO.CustomersDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
import DataBase.SQLinsertMultipleValues;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ErrorHandling.Errors.SQL_ERROR;

public class CustomersDB_DAO implements CustomersDAO {
    private final DButils dButils = new DButils();
    private final CouponsDB_DAO couponsDBDao = new CouponsDB_DAO();

    /**
     * Checks whether a customer exists in the DB
     * @param email - customer's email
     * @param password - customer's password
     * @return - true if customer exists, false if customer doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    public boolean IsCustomerExists(String email, String password) throws CouponSystemException {
        // Part 1 - prepare params
        Map<Integer,Object> params = dButils.PrepareParamsForLoginCheck(email,password);
        // Part 2 - run query for results in DB
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.isCustomerExists, params);
        // Part 3 - check results
        return dButils.CheckLoginResults(results);
    }

    /**
     * Returns a customer's ID based on email (unique)
     * @param email customer's email
     * @return customerID if customer exists, -1 if customer doesn't exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public int GetCustomerIDByEmail(String email) throws CouponSystemException {
        // Part 1 - Prepare params
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,email);
        // Part 2 - run query for results in DB
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.getCustomerIdByEmail,params);
        // Part 3 - check results and return companyID
        int customerID = -1;
        try {
            while(results.next()) {
                customerID = results.getInt(1);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return customerID;
    }

    /**
     * Checks whether a customer exists in the DB
     * @param customerID customer's id
     * @return true if customer exists, false if customer doesn't exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean IsCustomerIdExists(int customerID) throws CouponSystemException {
        // Part 1 - prepare params
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,customerID);
        // Part 2 - run query for results in DB
        ResultSet results = dButils.runQueryForResult(Read.isCustomerIdExists, params);
        // Part 3 - check results
        return dButils.CheckLoginResults(results);
    }

    /**
     * Adds a customer to the DB - adds the customer and the customer's coupon purchases (according to the param provided)
     * @param customer a 'Customer' class instance containing customer details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean AddCustomer(Customer customer) throws CouponSystemException {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer);
        // Part 1 - Prepare Hashmap and insert customer into DB
        Map<Integer,Object> params = PrepareParamsForAddCustomer(customers);
        if(dButils.runQueryWithMap(DataBase.CRUD.Create.insertCustomer,params) ) {
            if(customer.getCoupons() == null){
                return true;
            }
            if(customer.getCoupons().isEmpty()) {   // If no coupons linked to customer
                return true;
            }
            // Part 2 - Add customer coupon purchases into DB
            // Prepare params map for multiple coupon purchase
            Map<Integer, Integer> customerVsCouponsMap = new HashMap<>();
            for(Coupon coupon: customer.getCoupons()){
                customerVsCouponsMap.put(customer.getId(), coupon.getId());
            }
            params.clear();
            params = CouponsDB_DAO.PrepareParamsMapCouponPurchaseDel(customerVsCouponsMap);
            // Prepare multiple insert SQL statement
            String sql = dButils.sqlInsertMultipleValues(customer.getCoupons().size(), SQLinsertMultipleValues.CustomerVsCoupon);
            return dButils.runQueryWithMap(sql, params);
        }
        return false;
    }


    /**
     * Prepares 'param' map for adding customers to DB
     * @param customers an array list of customers to be added to params map
     * @return Map<Integer, Object> params if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private Map<Integer, Object> PrepareParamsForAddCustomer(ArrayList<Customer> customers) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        int count = 1;
        // Part 1 - Get customer's categoryID from DB:
        for(Customer customer: customers) {
            // Part 2 - prepare parameters
            params.put(count++,customer.getFirstName());
            params.put(count++,customer.getLastName());
            params.put(count++,customer.getEmail());
            params.put(count++,customer.getPassword());
        }
        return params;
    }


    /**
     * Updates a customer in the DB - according to the param provided
     * @param customer a 'Customer' class instance containing customer details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean UpdateCustomer(Customer customer) throws CouponSystemException {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer);
        // Prepare Hashmap and update customer in DB
        Map<Integer,Object> params = PrepareParamsForAddCustomer(customers);
        params.put(5,customer.getId());
        return dButils.runQueryWithMap(DataBase.CRUD.Update.updateCustomer,params);
            // Todo - Part 3 - update customer coupons in DB?
    }


    /**
     * Deletes a customer (according to the customer ID provided)
     * @param customerID a customer's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean DeleteCustomer(int customerID) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,customerID);
        // Part 2 - delete customer from DB
        return dButils.runQueryWithMap(Delete.deleteCustomer,params);
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
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.getAllCustomers,params);

        // Part 2 - add results to customer list
        return ConvertResultSetToCustomerArray(results);
    }


    /**
     * Gets a customer (according to the customer ID provided)
     * @param customerID a customer's ID, as listed in the DB
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Customer GetOneCustomer(int customerID) throws CouponSystemException {
        // Part 1 - Get customer - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,customerID);
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.getOneCustomer,params);

        // Part 2 - add results to customer list
        ArrayList<Customer> customers = ConvertResultSetToCustomerArray(results);
        if(customers.size() == 1){
            return customers.get(0);
        }
        return null;
    }


    /**
     * Converts a result set from SQL DB to an Array list of customer objects
     * @param results result set containing all Customers from DB
     * @return ArrayList<Customer> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Customer> ConvertResultSetToCustomerArray(ResultSet results) throws CouponSystemException {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            while (results.next()) {
                int id = results.getInt(1);
                String firstName = results.getString(2);
                String lastName = results.getString(3);
                String email = results.getString(4);
                String password = results.getString(5);

                // Todo - remove coupons section / mark 'null'?
                // Part 2 - Get coupons for customer - query from DB
                ArrayList<Coupon> coupons = couponsDBDao.GetCouponsForCustomer(id);

                // Create a new Customer object in the customerList
                customers.add(new Customer(id, firstName, lastName, email, password, coupons));
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return customers;
    }


    /**
     * Creates a map of customerIDs vs couponsID listed in DB.
     * @return Map<Integer, Integer> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Map<Integer, Integer> CustomerIDsVScouponIDs() throws CouponSystemException {
        // Part 1 - Get ResultSet of CustomerVSCoupons table from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = dButils.runQueryForResult(Read.getCustomersVsCoupons,params);

        // Part 2 - Insert results into customerIDsVScouponIDs map and return
        return InsertResultsToMapCustVSCoup(results);
    }

    /**
     * Converts a result set from SQL DB to a map of customerIDs vs couponsID
     * @param results result set containing all CustomersVScoupons from DB
     * @return Map<Integer, Integer> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private Map<Integer, Integer> InsertResultsToMapCustVSCoup(ResultSet results) throws CouponSystemException {
        Map<Integer, Integer> customerIDsVScouponIDs = new HashMap<>();
        try {
            while (results.next()) {
                int customerID = results.getInt(1);
                int couponID  = results.getInt(2);
                // Insert into customerIDsVScouponIDs map
                customerIDsVScouponIDs.put(customerID,couponID);
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return customerIDsVScouponIDs;
    }
}
