package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;

/**
 * Controller for Admin User
 */
@Validated
@CrossOrigin()
@RestController
@RequestMapping("/Admin")
@RequiredArgsConstructor
public class AdminController{
    private final AdminService adminService;
    private final JWT jwtUtil;

    //Todo - insert JWT authentication check - part 3

    /**
     * Get all Companies
     * @return A list of all companies in DB
     */
    @GetMapping(value = {"/GetAllCompanies"})
    public List<Company> GetAllCompanies(){
        return adminService.GetAllCompanies();
    }


    @GetMapping(value = {"/GetAllCompanies_Authorization"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> GetAllCompanies_Authorization
            (@RequestHeader("Authorization") String jwt)
            throws SignatureException {
        return new ResponseEntity<>(adminService.GetAllCompanies(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }


    /**
     * Get All Customers in DB
     * @return A list of all customers in DB
     */
    @GetMapping(value = {"/GetAllCustomers"})
    public List<Customer> GetAllCustomers(){
        return adminService.GetAllCustomers();
    }

    /**
     * Add a Company
     * @param company company object with details to be added
     * @throws AdminException If we get any exception.  Details are provided
     */
    @PostMapping(value = {"/AddCompany"})
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCompany(@Validated @RequestBody Company company) throws AdminException {
        adminService.AddCompany(company);
    }

    /**
     * Add a customer
     * @param customer customer object with details to be added
     * @throws AdminException  If we get any exception.  Details are provided
     */
    @PostMapping(value = {"/AddCustomer"})
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCustomer(@Validated @RequestBody Customer customer) throws AdminException {
        adminService.AddCustomer(customer);
    }

    /**
     * Update a Company
     * @param company company object with details to be updated
     * @throws AdminException If we get any exception.  Details are provided
     */
    @PutMapping("/UpdateCompany/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateCompany(@RequestBody Company company) throws AdminException, LoginException {
        adminService.UpdateCompany(company);
    }

    /**
     * Update a customer
     * @param customer customer object with details to be updated
     * @throws AdminException If we get any exception.  Details are provided
     */
    @PutMapping("/UpdateCustomer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateCustomer(@RequestBody Customer customer) throws AdminException, LoginException {
        adminService.UpdateCustomer(customer);
    }

    /**
     * Get One Company
     * @param id Id belonging to the company requested
     * @return company object with the requested company details
     * @throws AdminException  If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCompany/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company GetOneCompany(@PathVariable int id) throws AdminException {
        return adminService.GetOneCompany(id);
    }

    /**
     * Get one customer
     * @param id Id belonging to the customer requested
     * @return customer object with the requested customer details
     * @throws AdminException If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer GetOneCustomer(@PathVariable int id) throws AdminException {
        return adminService.GetOneCustomer(id);
    }

    /**
     * Delete a Company
     * @param id Id belonging to the company to be deleted
     * @throws AdminException If we get any exception.  Details are provided
     */
    @DeleteMapping("/DeleteCompany/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteCompany(@PathVariable int id) throws AdminException {
        adminService.DeleteCompanyCoupons(id);
        adminService.DeleteCompany(id);
    }

    /**
     * Delete a customer
     * @param id Id belonging to the customer to be deleted
     * @throws AdminException  If we get any exception.  Details are provided
     */
    @DeleteMapping("/DeleteCustomer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteCustomer(@PathVariable int id) throws AdminException {
        adminService.DeleteCustomer(id);
    }


}
