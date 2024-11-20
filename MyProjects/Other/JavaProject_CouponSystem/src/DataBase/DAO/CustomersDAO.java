package DataBase.DAO;

import Beans.Customer;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface used for creating customers DB DAO class
 */
public interface CustomersDAO {
    /**
     * Checks whether a customer exists in the DB
     * @param email - customer's email
     * @param password - customer's password
     * @return - true if customer exists, false if customer doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    boolean IsCustomerExists(String email, String password) throws CouponSystemException;

    /**
     * Returns a customer's ID based on email (unique)
     * @param email customer's email
     * @return customerID if customer exists, -1 if customer doesn't exist.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    int GetCustomerIDByEmail(String email) throws CouponSystemException;

    /**
     * Checks whether a customer exists in the DB
     * @param customerID customer's id
     * @return true if customer exists, false if customer doesn't exist.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean IsCustomerIdExists(int customerID) throws CouponSystemException;

    /**
     * Adds a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean AddCustomer(Customer customer) throws CouponSystemException;

    /**
     * Updates a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean UpdateCustomer(Customer customer) throws CouponSystemException;

    /**
     * Deletes a customer (according to the customer ID provided)
     * @param customerID a customer's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    boolean DeleteCustomer(int customerID) throws CouponSystemException;

    /**
     * Gets an ArrayList of all the customers listed in the DB
     * @return an ArrayList of 'Customer' class items if succeeded, 'null' if failed or if no customers exist.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    ArrayList<Customer> GetAllCustomers() throws CouponSystemException;

    /**
     * Gets a customer (according to data provided in params)
     * @param customerID a customer's ID, as listed in the DB
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    Customer GetOneCustomer(int customerID) throws CouponSystemException;

    /**
     * Creates a map of customerIDs vs couponsID listed in DB.
     * @return A map of integers and integers if succeeded, null if failed.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    Map<Integer, Integer> CustomerIDsVScouponIDs() throws CouponSystemException;
}
