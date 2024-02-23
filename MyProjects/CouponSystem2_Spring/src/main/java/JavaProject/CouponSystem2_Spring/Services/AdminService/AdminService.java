package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AdminService extends ClientService {

    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException;

    /**
     * Admin Method - Get One Customer
     * @throws AdminException If we get any exception.  Details are provided
     */
    void Method_GetOneCustomer() throws AdminException;

    /**
     * Admin Method - Delete Customer
     * @throws AdminException  If we get any exception.  Details are provided
     */
    void Method_DeleteCustomer() throws AdminException;

    /**
     * Admin Method - Update Customer
     * @throws AdminException If we get any exception.  Details are provided
     */
    void Method_UpdateCustomer() throws AdminException;

    /**
     * Admin Method - Add Customer
     * @throws AdminException  If we get any exception.  Details are provided
     */
    void Method_AddCustomer() throws AdminException;

    /**
     * Admin Method - Get All Customers
     * @throws AdminException If we get any exception.  Details are provided
     */
    void Method_GetAllCustomers() throws AdminException;

    /**
     * Admin Method - Get One Company
     * @throws AdminException  If we get any exception.  Details are provided
     */
    void Method_GetOneCompany() throws AdminException;

    /**
     * Admin Method - Delete Company
     * @throws AdminException If we get any exception.  Details are provided
     */
    void Method_DeleteCompany() throws AdminException;

    /**
     * Admin Method - Update Company
     * @throws AdminException If we get any exception.  Details are provided
     */
    void Method_UpdateCompany() throws AdminException;

    /**
     * Admin Method - Add Company
     * @throws AdminException If we get any exception.  Details are provided
     */
    void Method_AddCompany() throws AdminException;

    /**
     * Admin Method - Get all Companies
     * @throws AdminException  If we get any exception.  Details are provided
     */
    void Method_GetAllCompanies() throws AdminException;

    /**
     * After running all admin methods, add random company details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    String[] AddCompanyDetailsForLogin() throws AdminException;

    /**
     * After running all admin methods, add random customer details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    String[] AddCustomerDetailsForLogin() throws AdminException;
}
