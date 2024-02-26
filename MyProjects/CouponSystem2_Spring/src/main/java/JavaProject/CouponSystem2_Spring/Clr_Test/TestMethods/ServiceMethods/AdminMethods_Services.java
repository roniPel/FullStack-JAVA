package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminMethods_Services extends TestMethods {
    // Todo - Finish all methods

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
                .b_name(name)
                .c_email(email)
                .d_password(password)
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
    public void Method_AddCustomer(AdminService adminService) {
    }

    /**
     * Admin Method - Update Company
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_UpdateCompany(AdminService adminService) {
    }

    /**
     * Admin Method - Update Customer
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_UpdateCustomer(AdminService adminService) {
    }

    /**
     * Admin Method - Get One Company
     * @param adminService used to run method
     * @throws AdminException  If we get any exception.  Details are provided
     */
    public void Method_GetOneCompany(AdminService adminService) {
    }

    /**
     * Admin Method - Get One Customer
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_GetOneCustomer(AdminService adminService) {
    }

    /**
     * Admin Method - Delete Company
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_DeleteCompany(AdminService adminService) {
    }

    /**
     * Admin Method - Delete Customer
     * @param adminService used to run method
     * @throws AdminException  If we get any exception.  Details are provided
     */
    public void Method_DeleteCustomer(AdminService adminService) {
    }
}
