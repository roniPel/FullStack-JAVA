package DataBase.DAO;

import Beans.Customer;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;

public interface CustomersDAO {
    static boolean IsCustomerExists(String email, String password) throws CouponSystemException {return false;};
    static boolean AddCustomer(Customer customer) {return false;};
    static boolean UpdateCustomer(Customer customer) {return false;};
    static boolean deleteCustomer(int customerID) {return false;};
    static ArrayList<Customer> GetAllCustomers() {return null;};
    static Customer GetOneCustomer(int customerID) {return null;};
}
