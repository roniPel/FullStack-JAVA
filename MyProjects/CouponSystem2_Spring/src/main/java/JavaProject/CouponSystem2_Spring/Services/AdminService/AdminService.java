package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface AdminService extends ClientService {


    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException;

    /**
     * Admin Method - Get One Customer
     * @throws AdminException If we get any exception.  Details are provided
     */
    Customer GetOneCustomer(int customerId) throws AdminException;

    /**
     * Admin Method - Delete Customer
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean DeleteCustomer(int customerId) throws AdminException;

    /**
     * Admin Method - Update Customer
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean UpdateCustomer(Customer customer) throws AdminException;

    /**
     * Admin Method - Add Customer
     * @throws AdminException  If we get any exception.  Details are provided
     */
    boolean AddCustomer(Customer customer) throws AdminException;

    /**
     * Admin Method - Get All Customers
     */
    List<Customer> GetAllCustomers();

    /**
     * Admin Method - Get One Company
     * @throws AdminException  If we get any exception.  Details are provided
     */
    Company GetOneCompany(int companyId) throws AdminException;

    /**
     * Admin Method - Delete Company
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean DeleteCompany(int companyId) throws AdminException;

    /**
     * Admin Method - Update Company
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean UpdateCompany(Company company) throws AdminException;

    /**
     * Admin Method - Add Company
     * @throws AdminException If we get any exception.  Details are provided
     */
    boolean AddCompany(Company company) throws AdminException;

    /**
     * Admin Method - Get all Companies
     */
    List<Company> GetAllCompanies() ;

    /**
     * After running all admin methods, add company with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    String[] AddCompanyDetailsForLogin() throws AdminException, CompanyException;

    /**
     * After running all admin methods, add customer with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    String[] AddCustomerDetailsForLogin() throws AdminException;

    //Todo - write method
    boolean DeleteCompanyCoupons() throws AdminException;
}
