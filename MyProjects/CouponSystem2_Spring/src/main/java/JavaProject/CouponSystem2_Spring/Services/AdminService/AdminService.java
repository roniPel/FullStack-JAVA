package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import java.util.List;

/**
 * Admin Service interface - Lists relevant functionalities for Admin users
 */
public interface AdminService extends ClientService {

    @Override
    boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException;

    /**
     * Get one customer
     * @param customerId Id belonging to the customer requested
     * @return A customer object with requested customer details
     * @throws AdminException If we get any exception.  Details are provided
     */
    Customer GetOneCustomer(int customerId) throws AdminException;

    /**
     * Delete a customer
     * @param customerId Id belonging to the customer to be deleted
     * @return true if succeeded, false if failed
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean DeleteCustomer(int customerId) throws AdminException;

    /**
     * Update a customer
     * @param customer customer object with details to be updated
     * @return true if succeeded, false if failed
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean UpdateCustomer(Customer customer) throws AdminException;

    /**
     * Add a customer
     * @param customer customer object with details to be added
     * @return true if succeeded, false if failed
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean AddCustomer(Customer customer) throws AdminException;

    /**
     * Get All Customers in DB
     * @return A list of all customers in DB
     */
    List<Customer> GetAllCustomers();

    /**
     * Get One Company
     * @param companyId Id belonging to the company requested
     * @return A company object with requested company details
     * @throws AdminException  If we get any exception.  Details are provided
     */
    Company GetOneCompany(int companyId) throws AdminException;

    /**
     * Delete a Company
     * @param companyId Id belonging to the company to be deleted
     * @return true if succeeded, false if failed
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean DeleteCompany(int companyId) throws AdminException;

    /**
     * Update a Company
     * @param company company object with details to be updated
     * @return true if succeeded, false if failed
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean UpdateCompany(Company company) throws AdminException;

    /**
     * Add a Company
     * @param company company object with details to be added
     * @return true if succeeded, false if failed
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean AddCompany(Company company) throws AdminException;

    /**
     * Get all Companies
     * @return A list of all companies in DB
     */
    List<Company> GetAllCompanies() ;

    /**
     * After running all admin methods, add company with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     * @throws AdminException If we get any exception.  Details are provided
     */
    String[] AddCompanyDetailsForLogin() throws AdminException;

    /**
     * After running all admin methods, add customer with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @param companyId Id belonging to company that contains full coupons
     * @throws AdminException If we get any exception.  Details are provided
     */
    String[] AddCustomerDetailsForLogin(int companyId) throws AdminException;

    /**
     * Deletes company coupons (for a company that will be deleted)
     * @param companyId - company Id - marking coupons to be deleted
     * @return true if succeeded, false if failed.
     */
    boolean DeleteCompanyCoupons(int companyId) throws AdminException;

    /**
     * Deletes customer coupons (for a company that will be deleted)
     * @param customerId - customer Id - marking coupons to be deleted
     * @return true if succeeded, false if failed.
     */
    boolean DeleteCustomerCoupons(int customerId);
}
