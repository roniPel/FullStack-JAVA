package Facade;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DataBase.DAO.CompaniesDAO;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import DataBase.DAO.CustomersDAO;
import DataBase.DAO.DB_DAO.CompaniesDB_DAO;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;
import Utils.DateFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Admin Facade - for running admin methods
 */
public class AdminFacade extends ClientFacade{
    private final String email = "admin@admin.com";
    private final String password = "admin";
    
    private final CompaniesDAO companiesDAO = new CompaniesDB_DAO();
    private final CustomersDAO customersDAO = new CustomersDB_DAO();
    private final CouponsDAO couponsDAO = new CouponsDB_DAO();


    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    @Override
    public boolean Login(String email, String password) throws CouponSystemException {
        // Admin user - Check login details locally: (no need to check via DB query)
        if((Objects.equals(email, this.email)) && (Objects.equals(password, this.password))) {
            return true;
        }
        throw new CouponSystemException(Errors.INCORRECT_LOGIN_DETAILS);
    }

    /**
     * Adds a company to the DB
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean AddCompany(Company company) throws CouponSystemException {
        // Verify can't create company with same email or name -  covered by try-catch in DButils class
        return companiesDAO.AddCompany(company);
    }

    /**
     * Updates a company in the DB
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean UpdateCompany(Company company) throws CouponSystemException {
        // Part 1 - Verify can't update company ID - option not available in companiesDAO
        // Part 2 - Verify company exists in DB
        if (companiesDAO.IsCompanyIdExists(company.getId()) ) {
            // Part 3 - verify company name doesn't exist in DB
            ArrayList<Company> companies = GetAllCompanies();
            for(Company comp: companies) {
                if(company.getId() == comp.getId()){}
                else if (Objects.equals(comp.getName(), company.getName())) {
                    throw new CouponSystemException(Errors.COMPANY_NAME_ALREADY_EXISTS);
                }
            }
            return companiesDAO.UpdateCompany(company);
        }
        else {
            throw new CouponSystemException(Errors.COMPANY_DOES_NOT_EXIST);
        }
    }


    /**
     * Deletes a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean DeleteCompany(int companyID) throws CouponSystemException {
        // Verify company coupons are deleted as well - covered by DB table cascade config
        // Verify relevant company customer coupon purchases are deleted as well - covered by DB table cascade config
        // Part 1 - Verify company exists in DB
        Company company = companiesDAO.GetOneCompany(companyID);
        if (company == null) {
            throw new CouponSystemException(Errors.COMPANY_DOES_NOT_EXIST);
        }
        // Part 2 - Delete company from DB
        return companiesDAO.DeleteCompany(companyID);
    }


    /**
     * Gets an ArrayList of all the companies listed in the DB
     * @return an ArrayList of 'Company' class items if succeeded, 'null' if failed or if no companies exist.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public ArrayList<Company> GetAllCompanies() throws CouponSystemException {
        ArrayList<Company> companies = companiesDAO.GetAllCompanies();
        // Part 1 - Verify companies exist in DB
        if(companies == null) {
            throw new CouponSystemException(Errors.TABLE_IS_EMPTY);
        }
        // Part 2 - return companies array
        return companies;
    }


    /**
     * Gets a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public Company GetOneCompany(int companyID) throws CouponSystemException {
        Company company = companiesDAO.GetOneCompany(companyID);
        // Part 1 - Verify company exists in DB
        if(company == null) {
            throw new CouponSystemException(Errors.COMPANY_DOES_NOT_EXIST);
        }
        // Part 2 - return company
        return company;
    }


    /**
     * Adds a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean AddCustomer(Customer customer) throws CouponSystemException {
        // Verify can't add customer with same email - covered by try-catch in DButils class
        return customersDAO.AddCustomer(customer);
    }


    /**
     * Updates a customer to the DB, based on param
     * @param customer - 'Customer' object instance with all customer details
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean UpdateCustomer(Customer customer) throws CouponSystemException {
        // Part 1 - Verify can't update customer ID - option not available in customersDAO
        // Part 2 - Verify customer exists in DB
        if (customersDAO.IsCustomerIdExists(customer.getId()) ) {
            return customersDAO.UpdateCustomer(customer);
        }
        else {
            throw new CouponSystemException(Errors.CUSTOMER_DOES_NOT_EXIST);
        }
    }


    /**
     * Deletes a customer (according to the customer ID provided)
     * @param customerID a customer's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public boolean DeleteCustomer(int customerID) throws CouponSystemException {
        // Verify customer coupon purchases are also deleted - covered by DB table cascade config
        // Part 1 - Verify customer exists in DB
        Customer customer = customersDAO.GetOneCustomer(customerID);
        if (customer == null) {
            throw new CouponSystemException(Errors.CUSTOMER_DOES_NOT_EXIST);
        }
        // Part 2 - Delete customer from DB
        return customersDAO.DeleteCustomer(customerID);
    }


    /**
     * Gets an ArrayList of all the customers listed in the DB
     * @return an ArrayList of 'Customer' class items if succeeded, 'null' if failed or if no customers exist.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public ArrayList<Customer> GetAllCustomers() throws CouponSystemException {
        ArrayList<Customer> customers = customersDAO.GetAllCustomers();
        // Part 1 - Verify customers exist in DB
        if(customers == null) {
            throw new CouponSystemException(Errors.TABLE_IS_EMPTY);
        }
        // Part 2 - return customers array
        return customers;
    }


    /**
     * Gets a customer (according to data provided in params)
     * @param customerID a customer's ID, as listed in the DB
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public Customer GetOneCustomer(int customerID) throws CouponSystemException {
        Customer customer = customersDAO.GetOneCustomer(customerID);
        // Part 1 - Verify customer exists in DB
        if(customer == null) {
            throw new CouponSystemException(Errors.CUSTOMER_DOES_NOT_EXIST);
        }
        // Part 2 - return customer
        return customer;
    }

    /**
     * Add customer with coupons from all categories
     * @return the new customer id
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public int AddCustomerWithFullCoupons() throws CouponSystemException {
        // Add Customer to DB
        Customer customer = new Customer(50,"FirstFullCoupons", "LastFullCoupons","FullCoupons@email.com","Password",null);
        AddCustomer(customer);
        // Get customer ID from DB
        ArrayList<Customer> allCustomers = GetAllCustomers();
        int newCustomerId = -1;
        for(Customer cust: allCustomers) {
            if(Objects.equals(cust.getEmail(), customer.getEmail())) {
                newCustomerId= cust.getId();
                break;
            }
        }
        // Add coupons from all categories to DB and to customer
        ArrayList<Company> allCompanies = GetAllCompanies();
        Map<Integer, String> allCategories = couponsDAO.GetAllCategories();
        int count = 0;
        for (String value : allCategories.values()) {
            // Create coupon locally
            int companyId = allCompanies.get(0).getId();
            Category category = Category.valueOf(value);
            String title = "Title Customer "+category;
            String description = "Description Customer "+category;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            int amount = 10;
            double price = Math.random()*200;
            String image = "Image Customer "+category;
            Coupon addCoupon = new Coupon(count++,companyId,category,title,description,startDate,endDate,amount,price,
                    image);
            // Add coupon to DB
            couponsDAO.AddCoupon(addCoupon);
            int newCouponId = couponsDAO.GetCouponIDByTitle(title);
            // Add coupon purchase to customer
            couponsDAO.AddCouponPurchase(newCustomerId,newCouponId);
        }
        return newCustomerId;
    }

    /**
     * Add coupons from all categories to a new company
     * @return the new company id
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public int AddCompanyWithFullCoupons() throws CouponSystemException {
        // Add Customer to DB
        Company company = new Company(100,"CompanyFullCoupons","CompCoupons@email.com","Pass",null);
        AddCompany(company);
        // Get company ID from DB
        ArrayList<Company> allCompanies = GetAllCompanies();
        int newCompanyId = -1;
        for(Company comp: allCompanies) {
            if(Objects.equals(comp.getEmail(), company.getEmail())) {
                newCompanyId= comp.getId();
                break;
            }
        }
        // Add coupons from all categories to DB (to company
        Map<Integer, String> allCategories = couponsDAO.GetAllCategories();
        int count = 0;
        for (String value : allCategories.values()) {
            // Create coupon locally
            Category category = Category.valueOf(value);
            String title = "Title Company "+category;
            String description = "Description Company "+category;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            int amount = 10;
            double price = Math.random()*200;
            String image = "Image Company "+category;
            Coupon addCoupon = new Coupon(count++,newCompanyId,category,title,description,startDate,endDate,amount,price,
                    image);
            // Add coupon to DB
            couponsDAO.AddCoupon(addCoupon);
        }
        return newCompanyId;
    }

}
