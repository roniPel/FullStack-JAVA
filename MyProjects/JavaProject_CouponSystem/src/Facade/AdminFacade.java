package Facade;

import Beans.Company;
import Beans.Customer;

import java.util.ArrayList;

public class AdminFacade extends  ClientFacade{
    //Todo - finish all class methods

    public AdminFacade() {
    }

    @Override
    public boolean Login(String email, String password) {
        return false;
    }

    public static boolean AddCompany(Company company) {
        return false;
    }

    public static boolean UpdateCompany(Company company) {
        return false;
    }
    public static boolean DeleteCompany(int companyID) {
        return false;
    }

    public static ArrayList<Company> GetAllCompanies() {
        return null;
    }
    public static Company GetOneCompany(int companyID) {
        return null;
    }
    public static boolean AddCustomer(Customer customer) {
        return false;
    }

    public static boolean UpdateCustomer(Customer customer) {
        return false;
    }
    public static boolean DeleteCustomer(int customerID) {
        return false;
    }
    public static ArrayList<Customer> GetAllCustomers() {
        return null;
    }
    public static Customer GetOneCustomer(int customerID) {
        return null;
    }
}
