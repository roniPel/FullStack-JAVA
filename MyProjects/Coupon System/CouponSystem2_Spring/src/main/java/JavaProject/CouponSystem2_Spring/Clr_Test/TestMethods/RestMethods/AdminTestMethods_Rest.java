package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Admin Test Methods Class - Used for Testing all Admin user functionalities via RestTemplate
 */
@Component
@RequiredArgsConstructor
public class AdminTestMethods_Rest extends TestMethods {
    private final RestTemplate restTemplate;

    /**
     * Admin Method - Get all Companies
     */
    public void Method_GetAllCompanies() {
        System.out.println("*** Method: Get All Companies ***");
        List<Company> companyList = GetListOfAllCompanies();
        companyList.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Admin Method - Get All Customers
     */
    public void Method_GetAllCustomers() {
        System.out.println("*** Method: Get All Customers ***");
        List<Customer> customerList = GetListOfAllCustomers();
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
                ("http://localhost:8080/Admin/AddCompany",addCompany,String.class);
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
                ("http://localhost:8080/Admin/AddCustomer",addCustomer,String.class);
        System.out.print("Added Company? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    /**
     * Admin Method - Update Company
     */
    public void Method_UpdateCompany() {
        System.out.println("*** Method: Update Company ***");
        // Get all companies from DB
        List<Company> companyList = GetListOfAllCompanies();

        // Select random ID for updating company
        int updateCompId = GetRandIdFromList(companyList);
        Company updatedComp = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetOneCompany/"+updateCompId,Company.class);

        // Update fields
        updatedComp.setEmail("Rest_AdminUpdate"+GetrandInt(100)+"@email.com");
        updatedComp.setPassword("PassUpd");

        // Update company in DB
        System.out.println("Company to update: ");
        System.out.println(updatedComp);
        restTemplate.put
                ("http://localhost:8080/Admin/UpdateCompany/"+updatedComp.getId(),updatedComp);
        System.out.println("Updated Company? true");
        System.out.println();
    }

    /**
     * Admin Method - Update Customer
     */
    public void Method_UpdateCustomer() {
        System.out.println("*** Method: Update Customer ***");

        // Get all customers from DB
        List<Customer> customerList = GetListOfAllCustomers();

        // Select random ID for updating
        int updateCustId = GetRandIdFromList(customerList);
        Customer updatedCust = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetOneCustomer/"+updateCustId,Customer.class);

        // Update fields
        updatedCust.setFirstName("Rest_UpdateFirst");
        updatedCust.setLastName("Rest_UpdateLast");
        updatedCust.setEmail("Rest_UpdEmail"+GetrandInt(100)+"@email.com");

        // Update customer in DB
        System.out.println("Customer to update: ");
        System.out.println(updatedCust);
        restTemplate.put
                ("http://localhost:8080/Admin/UpdateCustomer/"+updatedCust.getId(),updatedCust);
        System.out.println("Updated Customer? true");
        System.out.println();
    }

    /**
     * Admin Method - Get One Company
     */
    public void Method_GetOneCompany() {
        System.out.println("*** Method: Get One Company ***");
        List<Company> companies = GetListOfAllCompanies();
        // Pick random Id from companies
        int getOneCompId = GetRandIdFromList(companies);
        Company company = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetOneCompany/"+getOneCompId,Company.class);
        // Print company
        System.out.println("One Company: \n"+company);
        System.out.println();
    }

    /**
     * Admin Method - Get One Customer
     */
    public void Method_GetOneCustomer() {
        System.out.println("*** Method: Get One Customer ***");
        List<Customer> customers = GetListOfAllCustomers();
        // Get random ID
        int getOneCustId = GetRandIdFromList(customers);
        Customer customer = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetOneCustomer/"+getOneCustId,Customer.class);
        // Print customer
        System.out.println("One Customer: \n"+customer);
        System.out.println();
    }

    /**
     * Admin Method - Delete Company
     */
    public void Method_DeleteCompany() {
        System.out.println("*** Method: Delete Company ***");
        List<Company> companies = GetListOfAllCompanies();
        // Select random ID for deletion
        int delCompId = GetRandIdFromList(companies);
        Company compToDelete = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetOneCompany/"+delCompId, Company.class);
        System.out.println("Company to delete: \n"+compToDelete);
        // Delete company
        restTemplate.delete("http://localhost:8080/Admin/DeleteCompany/"+delCompId);
        System.out.println("Deleted Company? true");
        System.out.println();
    }

    /**
     * Admin Method - Delete Customer
     */
    public void Method_DeleteCustomer() {
        System.out.println("*** Method: Delete Customer ***");
        List<Customer> customers = GetListOfAllCustomers();
        // Select random Id for deleting
        int delCustId = GetRandIdFromList(customers);
        Customer custToDelete = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetOneCustomer/"+delCustId, Customer.class);
        System.out.println("Customer to delete: \n"+custToDelete);
        // Delete customer
        restTemplate.delete("http://localhost:8080/Admin/DeleteCustomer/"+delCustId);
        System.out.println("Deleted Customer? true");
        System.out.println();
    }

    /**
     * Creates a list of all companies (convert array to list)
     * @return List of all companies in DB
     */
    private List<Company> GetListOfAllCompanies() {
        Company[] companies = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetAllCompanies", Company[].class);
        return Arrays.stream(companies).toList();
    }

    /**
     * Creates a list of all customers (convert array to list)
     * @return List of all customers in DB
     */
    private List<Customer> GetListOfAllCustomers() {
        Customer[] customers = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetAllCustomers", Customer[].class);
        return Arrays.stream(customers).toList();
    }
}
