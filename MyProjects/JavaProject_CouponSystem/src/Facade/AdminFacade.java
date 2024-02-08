package Facade;

import Beans.Company;
import Beans.Customer;
import DataBase.DAO.CompaniesDAO;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import DataBase.DAO.CustomersDAO;
import DataBase.DAO.DB_DAO.CompaniesDB_DAO;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;

import java.util.ArrayList;
import java.util.Objects;

public class AdminFacade extends ClientFacade{
    private final String email = "admin@admin.com";
    private final String password = "admin";
    
    private final CompaniesDAO companiesDAO = new CompaniesDB_DAO();
    private final CustomersDAO customersDAO = new CustomersDB_DAO();



    public AdminFacade() {
    }

    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    @Override
    public boolean Login(String email, String password) throws CouponSystemException {
        // Admin user - Check login details locally: (no need to check via DB query)
        if((Objects.equals(email, this.email)) && (Objects.equals(password, this.password))) {
            return true;
        }
        throw new CouponSystemException(Errors.INCORRECT_LOGIN_DETAILS.getMessage());
    }

    /**
     * Adds a company to the DB
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean AddCompany(Company company) throws CouponSystemException {
        // Verify can't create company with same email or name -  covered by try-catch in DButils class
        return companiesDAO.AddCompany(company);
    }

    /**
     * Updates a company in the DB
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean UpdateCompany(Company company) throws CouponSystemException {
        // Part 1 - Verify can't update company ID - option not available in companiesDAO
        // Part 2 - Verify company exists in DB
        if (companiesDAO.IsCompanyIdExists(company.getId()) ) {
            // Part 3 - verify company name doesn't exist in DB
            ArrayList<Company> companies = GetAllCompanies();
            for(Company comp: companies) {
                if (Objects.equals(comp.getName(), company.getName())) {
                    throw new CouponSystemException(Errors.COMPANY_NAME_ALREADY_EXISTS.getMessage());
                }
            }
            return companiesDAO.UpdateCompany(company);
        }
        else {
            throw new CouponSystemException(Errors.COMPANY_DOES_NOT_EXIST.getMessage());
        }
    }


    /**
     * Deletes a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean DeleteCompany(int companyID) throws CouponSystemException {
        // Verify company coupons are deleted as well - covered by DB table cascade config
        // Verify relevant company customer coupon purchases are deleted as well - covered by DB table cascade config
        // Part 1 - Verify company exists in DB
        Company company = companiesDAO.GetOneCompany(companyID);
        if (company == null) {
            throw new CouponSystemException(Errors.COMPANY_DOES_NOT_EXIST.getMessage());
        }
        // Part 2 - Delete company from DB
        return companiesDAO.DeleteCompany(companyID);
    }


    /**
     * Gets an ArrayList of all the companies listed in the DB
     * @return an ArrayList of 'Company' class items if succeeded, 'null' if failed or if no companies exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Company> GetAllCompanies() throws CouponSystemException {
        ArrayList<Company> companies = companiesDAO.GetAllCompanies();
        // Part 1 - Verify companies exist in DB
        if(companies == null) {
            throw new CouponSystemException(Errors.TABLE_IS_EMPTY.getMessage());
        }
        // Part 2 - return companies array
        return companies;
    }


    /**
     * Gets a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Company GetOneCompany(int companyID) throws CouponSystemException {
        Company company = companiesDAO.GetOneCompany(companyID);
        // Part 1 - Verify company exists in DB
        if(company == null) {
            throw new CouponSystemException(Errors.COMPANY_DOES_NOT_EXIST.getMessage());
        }
        // Part 2 - return company
        return company;
    }


    /**
     * Adds a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    public boolean AddCustomer(Customer customer) throws CouponSystemException {
        // Verify can't add customer with same email - covered by try-catch in DButils class
        return customersDAO.AddCustomer(customer);
    }


    /**
     * Updates a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    public boolean UpdateCustomer(Customer customer) throws CouponSystemException {
        // Part 1 - Verify can't update customer ID - option not available in customersDAO
        // Part 2 - Verify customer exists in DB
        if (customersDAO.IsCustomerIdExists(customer.getId()) ) {
            return customersDAO.UpdateCustomer(customer);
        }
        else {
            throw new CouponSystemException(Errors.CUSTOMER_DOES_NOT_EXIST.getMessage());
        }
    }


    /**
     * Deletes a customer (according to the customer ID provided)
     * @param customerID a customer's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean DeleteCustomer(int customerID) throws CouponSystemException {
        // Verify customer coupon purchases are also deleted - covered by DB table cascade config
        // Part 1 - Verify customer exists in DB
        Customer customer = customersDAO.GetOneCustomer(customerID);
        if (customer == null) {
            throw new CouponSystemException(Errors.CUSTOMER_DOES_NOT_EXIST.getMessage());
        }
        // Part 2 - Delete customer from DB
        return customersDAO.DeleteCustomer(customerID);
    }


    /**
     * Gets an ArrayList of all the customers listed in the DB
     * @return an ArrayList of 'Customer' class items if succeeded, 'null' if failed or if no customers exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Customer> GetAllCustomers() throws CouponSystemException {
        ArrayList<Customer> customers = customersDAO.GetAllCustomers();
        // Part 1 - Verify customers exist in DB
        if(customers == null) {
            throw new CouponSystemException(Errors.TABLE_IS_EMPTY.getMessage());
        }
        // Part 2 - return customers array
        return customers;
    }


    /**
     * Gets a customer (according to data provided in params)
     * @param customerID a customer's ID, as listed in the DB
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Customer GetOneCustomer(int customerID) throws CouponSystemException {
        Customer customer = customersDAO.GetOneCustomer(customerID);
        // Part 1 - Verify customer exists in DB
        if(customer == null) {
            throw new CouponSystemException(Errors.CUSTOMER_DOES_NOT_EXIST.getMessage());
        }
        // Part 2 - return customer
        return customer;
    }
}
