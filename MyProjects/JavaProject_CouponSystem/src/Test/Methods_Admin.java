package Test;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import ErrorHandling.CouponSystemException;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Utils.DateFactory;

import java.util.ArrayList;

/**
 * Admin Methods class - contains methods used for testing Admin user
 */
public class Methods_Admin extends Methods {
    /**
     * Admin Method - Get One Customer
     * @param adminFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    void Method_GetOneCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get One Customer ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        // Get random ID
        int getOneCustId = GetRandIdFromCustomerArray(customers);
        System.out.println("One Customer: ");
        System.out.println(adminFacade.GetOneCustomer(getOneCustId));
    }

    /**
     * Admin Method - Delete Customer
     * @param adminFacade used to run method
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    void Method_DeleteCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Customer ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        // Select random Id for deleting
        int delCustId = GetRandIdFromCustomerArray(customers);
        System.out.println("Deleted Customer? "+
                adminFacade.DeleteCustomer(delCustId) );
        System.out.println();
    }

    /**
     * Admin Method - Update Customer
     * @param adminFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    void Method_UpdateCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Customer ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        // Select random ID for updating
        int updateCustId = GetRandIdFromCustomerArray(customers);
        // Update fields
        Customer updatedCust = new Customer(updateCustId,"UpdatedFirstAdmin",
                "UpdatedLastAdmin","updatedEmail@email.com","PassAdmin",null);
        System.out.println("Updated Customer? "+
                adminFacade.UpdateCustomer(updatedCust));
        System.out.println();
    }

    /**
     * Admin Method - Add Customer
     * @param adminFacade used to run method
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    void Method_AddCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Add Customer ***");
        // Create customer
        Customer addCustomer = new Customer(50,"FirstAdminAdd", "LastAdminAdd","custAdmin@email.com","PassAdmin",null);
        System.out.println("Added Customer? "+ adminFacade.AddCustomer(addCustomer));
        System.out.println();
    }

    /**
     * Admin Method - Get All Customers
     * @param adminFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    void Method_GetAllCustomers(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get All Customers ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        customers.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Admin Method - Get One Company
     * @param adminFacade used to run method
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    void Method_GetOneCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get One Company ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        int getOneCompId = GetRandIdFromCompanyArray(companies);
        System.out.println("One Company: "+
                adminFacade.GetOneCompany(getOneCompId));
    }

    /**
     * Admin Method - Delete Company
     * @param adminFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    void Method_DeleteCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Company ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        // Select random ID for deletion
        int delCompId = GetRandIdFromCompanyArray(companies);
        System.out.println("Deleted Company? "+
                adminFacade.DeleteCompany(delCompId) );
        System.out.println();
    }

    /**
     * Admin Method - Update Company
     * @param adminFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    void Method_UpdateCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Company ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        // Select random ID for updating company
        int updateCompId = GetRandIdFromCompanyArray(companies);
        Company updatedComp = null;
        for (Company company: companies){
            if(company.getId() == updateCompId){
                updatedComp = company;
                break;
            }
        }
        // Update fields
        updatedComp.setEmail("AdminUpdateComp"+GetrandInt(10)+"@email.com");
        updatedComp.setPassword("PassUpd");
        updatedComp.setCoupons(null);
        // Update company
        System.out.println("Updated Company? "+
                adminFacade.UpdateCompany(updatedComp));
        System.out.println();
    }

    /**
     * Admin Method - Add Company
     * @param adminFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    void Method_AddCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Add Company ***");
        // Create company
        Company addCompany = new Company(150, "CompanyAddByAdmin","AdminAddComp"+GetrandInt(10)+"@email.com","PassAdmin",null);
        System.out.println("Added Company? "+ adminFacade.AddCompany(addCompany));
        System.out.println();
    }

    /**
     * Admin Method - Get all Companies
     * @param adminFacade used to run method
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    void Method_GetAllCompanies(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get All Companies ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        companies.forEach(System.out::println);
        System.out.println();
    }

    /**
     * After running all admin methods, add random company details for login
     * @param adminFacade Facade used to query data in DB
     * @return String array with email and password that exist in the DB
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    String[] AddCompanyDetailsForLogin(AdminFacade adminFacade) throws CouponSystemException {
        int companyId = adminFacade.AddCompanyWithFullCoupons();
        String[] compDetails = new String[2];
        Company company = adminFacade.GetOneCompany(companyId);
        compDetails[0] = company.getEmail();
        compDetails[1] = company.getPassword();
        return compDetails;
    }

    String[] AddCustomerDetailsForLogin(AdminFacade adminFacade) throws CouponSystemException {
        int customerId = adminFacade.AddCustomerWithFullCoupons();
        String[] custDetails = new String[2];
        Customer customer = adminFacade.GetOneCustomer(customerId);
        custDetails[0] = customer.getEmail();
        custDetails[1] = customer.getPassword();
        return custDetails;
    }

}
