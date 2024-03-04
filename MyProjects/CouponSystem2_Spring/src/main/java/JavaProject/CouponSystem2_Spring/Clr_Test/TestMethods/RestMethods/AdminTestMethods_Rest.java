package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminTestMethods_Rest extends TestMethods {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Admin Method - Get all Companies
     */
    public void Method_GetAllCompanies() {
        System.out.println("*** Method: Get All Companies ***");
        Company[] companies = restTemplate.getForObject
                ("http://localhost:8080/api/Admin/GetAllCompanies", Company[].class);
        List<Company> companyList = Arrays.stream(companies).toList();
        companyList.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Admin Method - Get All Customers
     */
    public void Method_GetAllCustomers() {
        System.out.println("*** Method: Get All Customers ***");
        Customer[] customers = restTemplate.getForObject
                ("http://localhost:8080/api/Admin/GetAllCustomers", Customer[].class);
        List<Customer> customerList = Arrays.stream(customers).toList();
        customerList.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Admin Method - Add Company
     */
    public void Method_AddCompany() {
        System.out.println("*** Method: Add Company ***");
        // Create company
        String name = "Rest_AddByAdmin";
        String email = "Rest_AdminAdd"+GetrandInt(100)+"@email.com";
        String password = "PassAdmin";
        Company addCompany = Company.builder()
                .id(6)
                .name(name)
                .email(email)
                .password(password)
                //.coupons(null)
                .build();
        System.out.println("Company to add: ");
        System.out.println(addCompany);

        // Add company to DB
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/api/Admin/AddCompany",addCompany,String.class);
        System.out.print("Added Company? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    /**
     * Admin Method - Add Customer
     */
    public void Method_AddCustomer() {
        System.out.println("*** Method: Add Customer ***");
        // Create customer
        String firstName = "Rest_AdminAdd";
        String lastName = "Rest_AdminAdd";
        String email = "RestAdmin"+GetrandInt(100)+"@email.com";
        String password = "PassAdmin";
        Customer addCustomer = Customer.builder()
                .id(13)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                //.coupon(null)
                .build();
        System.out.println("Customer to add: ");
        System.out.println(addCustomer);
        // Add customer to DB
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/api/Admin/AddCustomer",addCustomer,String.class);
        System.out.print("Added Company? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    /**
     * Admin Method - Update Company
     * @param adminService used to run method
     * @throws AdminException If we get any exception.  Details are provided
     */
    public void Method_UpdateCompany(AdminService adminService) throws AdminException {
        System.out.println("*** Method: Update Company ***");
        // Get all companies from DB
        Company[] companies = restTemplate.getForObject
                ("http://localhost:8080/api/Admin/GetAllCompanies", Company[].class);
        List<Company> companyList = Arrays.stream(companies).toList();

        // Select random ID for updating company
        int updateCompId = GetRandIdFromList(companyList);
        Company updatedComp = restTemplate.getForObject
                ("http://localhost:8080/api/Admin/GetOneCompany/"+updateCompId,Company.class);

        // Update fields
        updatedComp.setEmail("AdminUpdateComp"+GetrandInt(100)+"@email.com");
        updatedComp.setPassword("PassUpd");

        // Update company in DB
        System.out.println("Company to update: ");
        System.out.println(updatedComp);
        restTemplate.put
                ("http://localhost:8080/api/Admin/UpdateCompany/"+updatedComp.getId(),updatedComp);
        System.out.println("Updated Company? true");
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
                .firstName("UpdatedFirstAdmin")
                .lastName("UpdatedLastAdmin")
                .email("updatedEmail"+GetrandInt(100)+"@email.com")
                .password("PassAdmin")
                .build();
        System.out.println("Customer to update: ");
        System.out.println(updatedCust);
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
        System.out.println();
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
        System.out.println();
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
        System.out.println(adminService.GetOneCompany(delCompId));
        // Delete company coupons + delete company
        System.out.println("Deleted Company? "+ (
                adminService.DeleteCompanyCoupons(delCompId) && adminService.DeleteCompany(delCompId) ) );
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
        Customer customerToDelete = adminService.GetOneCustomer(delCustId);
        System.out.println(customerToDelete);
        // Delete company coupons + delete company
        System.out.println("Deleted Customer? "+(
                adminService.DeleteCustomerCoupons(delCustId) && adminService.DeleteCustomer(delCustId) ) );
        System.out.println();
    }

}
