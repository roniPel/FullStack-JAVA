package DataBase.DAO;

import Beans.Customer;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;

public interface CustomersDAO {
    /**
     * Checks whether a customer exists in the DB
     * @param email - customer's email
     * @param password - customer's password
     * @return - true if customer exists, false if customer doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    static boolean IsCustomerExists(String email, String password) throws CouponSystemException {return false;};

    /**
     * Adds a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    static boolean AddCustomer(Customer customer) throws CouponSystemException {return false;};

    /**
     * Updates a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    static boolean UpdateCustomer(Customer customer) throws CouponSystemException {return false;};

    /**
     * Delete a customer to the DB, based on param
     * @param customerID - customer ID belonging to customer user wants to delete
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    static boolean deleteCustomer(int customerID) throws CouponSystemException {return false;};

    /**
     * Gets an ArrayList of all the customers listed in the DB
     * @return an ArrayList of 'Customer' class items if succeeded, 'null' if failed or if no customers exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static ArrayList<Customer> GetAllCustomers() throws CouponSystemException {return null;};

    /**
     * Gets a customer (according to data provided in params)
     * @param customerID a customer's ID, as listed in the DB
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static Customer GetOneCustomer(int customerID) throws CouponSystemException {return null;};
}
