package DataBase.DAO.DB_DAO;

import Beans.Customer;
import DataBase.DAO.CustomersDAO;
import DataBase.ConnectionPool;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<Customer> GetAllCustomers() {
        return null;
    }

    public Customer GetOneCustomer(int customerID) {
        return null;
    }
}