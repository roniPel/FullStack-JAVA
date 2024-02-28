package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminMethods_Services extends TestMethods {

    /**
     * Admin Method - Get all Companies
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_GetAllCompanies(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Get All Companies ***");
        List<Company> companies = adminService.GetAllCompanies();
        companies.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Admin Method - Get All Customers
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_GetAllCustomers(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Get All Customers ***");
        List<Customer> customers = adminService.GetAllCustomers();
        customers.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Admin Method - Add Company
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_AddCompany(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Add Company ***");
        // Create company
        String name = "CompanyAddByAdmin";
        String email = "AdminAddComp"+GetrandInt(100)+"@email.com";
        String password = "PassAdmin";
        Company addCompany = Company.builder()
                .name(name)
                .email(email)
                .password(password)
                .coupons(null)
                .build();

        System.out.println("Company to add: ");
        System.out.print(addCompany);
        System.out.println("Added Company? "+ adminService.AddCompany(addCompany));
        System.out.println();
    }

    /**
     * Admin Method - Add Customer
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_AddCustomer(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Add Customer ***");
        // Create customer
        String firstName = "FirstAdminAdd";
        String lastName = "LastAdminAdd";
        String email = "custAdmin"+GetrandInt(100)+"@email.com";
        String password = "PassAdmin";
        Customer addCustomer = Customer.builder()
                .id(11)
                .first_name(firstName)
                .last_name(lastName)
                .email(email)
                .password(password)
                .coupon(null)
                .build();
        System.out.println("Customer to add: ");
        System.out.print(addCustomer);
        // Add customer to DB
        System.out.println("Added Customer? "+ adminService.AddCustomer(addCustomer));
        System.out.println();
    }

    /**
     * Admin Method - Update Company
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_UpdateCompany(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Update Company ***");
        List<Company> companies = adminService.GetAllCompanies();
        // Select random ID for updating company
        int updateCompId = GetRandIdFromList(companies);
        Company updatedComp = adminService.GetOneCompany(updateCompId);
        // Update fields
        updatedComp.setEmail("AdminUpdateComp"+GetrandInt(100)+"@email.com");
        updatedComp.setPassword("PassUpd");
        updatedComp.setCoupons(null);
        // Update company in DB
        System.out.println("Company to update: ");
        System.out.print(updatedComp);
        System.out.println("Updated Company? "+
                adminService.UpdateCompany(updatedComp));
        System.out.println();
    }

    /**
     * Admin Method - Update Customer
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_UpdateCustomer(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Update Customer ***");
        List<Customer> customers = adminService.GetAllCustomers();
        // Select random ID for updating
        int updateCustId = GetRandIdFromList(customers);
        // Update fields
        Customer updatedCust = Customer.builder()
                .id(updateCustId)
                .first_name("UpdatedFirstAdmin")
                .last_name("UpdatedLastAdmin")
                .email("updatedEmail"+GetrandInt(100)+"@email.com")
                .password("PassAdmin")
                .coupons(null)
                .build();
        System.out.println("Customer to update: ");
        System.out.print(updatedCust);
        // Update customer in DB
        System.out.println("Updated Customer? "+
                adminService.UpdateCustomer(updatedCust));
        System.out.println();
    }

    /**
     * Admin Method - Get One Company
     * @param adminService used to run method
     * @throws AdminException  If we get any exception.  Details are provided
     */
    public void Method_GetOneCompany(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Get One Company ***");
        List<Company> companies = adminService.GetAllCompanies();
        int getOneCompId = GetRandIdFromList(companies);
        System.out.println("One Company: "+
                adminService.GetOneCompany(getOneCompId));
    }

    /**
     * Admin Method - Get One Customer
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_GetOneCustomer(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Get One Customer ***");
        List<Customer> customers = adminService.GetAllCustomers();
        // Get random ID
        int getOneCustId = GetRandIdFromList(customers);
        System.out.println("One Customer: ");
        System.out.println(adminService.GetOneCustomer(getOneCustId));
    }

    /**
     * Admin Method - Delete Company
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_DeleteCompany(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Delete Company ***");
        List<Company> companies = adminService.GetAllCompanies();
        // Select random ID for deletion
        int delCompId = GetRandIdFromList(companies);
        System.out.println("Company to delete: ");
        System.out.print(adminService.GetOneCompany(delCompId));
        System.out.println("Deleted Company? "+
                adminService.DeleteCompany(delCompId) );
        System.out.println();
    }

    /**
     * Admin Method - Delete Customer
     * @param adminService used to run method
     * @throws AdminException  If we get any exception.  Details are provided
     */
    public void Method_DeleteCustomer(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Delete Customer ***");
        List<Customer> customers = adminService.GetAllCustomers();
        // Select random Id for deleting
        int delCustId = GetRandIdFromList(customers);
        System.out.println("Customer to delete: ");
        System.out.print(adminService.GetOneCustomer(delCustId));
        System.out.println("Deleted Customer? "+
                adminService.DeleteCustomer(delCustId) );
        System.out.println();
    }
}
