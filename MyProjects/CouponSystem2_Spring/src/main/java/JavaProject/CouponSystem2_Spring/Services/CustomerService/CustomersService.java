package JavaProject.CouponSystem2_Spring.Services.CustomerService;

import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.ArrayList;
import java.util.Map;

public interface CustomersService {

    /**
     * Checks whether a customer exists in the DB
     * @param email - customer's email
     * @param password - customer's password
     * @return - true if customer exists, false if customer doesn't exist or if the email + password combo are incorrect.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    boolean IsCustomerExists(String email, String password) throws CustomerException;


    /**
     * Returns a customer's ID based on email (unique)
     * @param email customer's email
     * @return customerID if customer exists, -1 if customer doesn't exist.
     * @throws CustomerException  If we get any exception.  Details are provided
     */
    int GetCustomerIDByEmail(String email) throws CustomerException;


    /**
     * Checks whether a customer exists in the DB
     * @param customerID customer's id
     * @return true if customer exists, false if customer doesn't exist.
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean IsCustomerIdExists(int customerID) throws AdminException;


    /**
     * Adds a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean AddCustomer(Customer customer) throws AdminException;


    /**
     * Updates a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean UpdateCustomer(Customer customer) throws AdminException;


    /**
     * Deletes a customer (according to the customer ID provided)
     * @param customerID a customer's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean DeleteCustomer(int customerID) throws AdminException;


    /**
     * Gets an ArrayList of all the customers listed in the DB
     * @return an ArrayList of 'Customer' class items if succeeded, 'null' if failed or if no customers exist.
     * @throws AdminException If we get any exception.  Details are provided
     */
    ArrayList<Customer> GetAllCustomers() throws AdminException;


    /**
     * Gets a customer (according to data provided in params)
     * @param customerID a customer's ID, as listed in the DB
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws AdminException,CustomerException  If we get any exception.  Details are provided
     */
    Customer GetOneCustomer(int customerID) throws AdminException,CustomerException;


    /**
     * Creates a map of customerIDs vs couponsID listed in DB.
     * @return A map of integers and integers if succeeded, null if failed.
     * @throws AdminException  If we get any exception.  Details are provided
     */
    Map<Integer, Integer> CustomerIDsVScouponIDs() throws AdminException;
}
