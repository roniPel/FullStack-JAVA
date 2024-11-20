package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    /**
     * Get all Companies
     * @return A list of all companies in DB
     */
    @GetMapping(value = {"/GetAllCompanies"})
    @ResponseStatus(HttpStatus.OK)
//    public List<Company> GetAllCompanies(){
//        return adminService.GetAllCompanies();
//    }
    public ResponseEntity<?> GetAllCompanies
            (@RequestHeader("Authorization") String jwt)
            throws SignatureException {
        return new ResponseEntity<>(adminService.GetAllCompanies(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get All Customers in DB
     * @return A list of all customers in DB
     */
    @GetMapping(value = {"/GetAllCustomers"})
//    public List<Customer> GetAllCustomers(){
//        return adminService.GetAllCustomers();
//    }
    public ResponseEntity<?> GetAllCustomers
            (@RequestHeader("Authorization") String jwt)
            throws SignatureException {
        return new ResponseEntity<>(adminService.GetAllCustomers(), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }

    /**
     * Add a Company
     * @param company company object with details to be added
     * @throws AdminException If we get any exception.  Details are provided
     */
    @PostMapping(value = {"/AddCompany"})
    @ResponseStatus(HttpStatus.CREATED)
//    public void AddCompany(@Validated @RequestBody Company company) throws AdminException {
//        adminService.AddCompany(company);
//    }
    public ResponseEntity<?> AddCompany
            (@RequestHeader("Authorization") String jwt,@Validated @RequestBody Company company)
            throws AdminException, SignatureException {
        return new ResponseEntity<>(adminService.AddCompany(company), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }

    /**
     * Add a customer
     * @param customer customer object with details to be added
     * @throws AdminException  If we get any exception.  Details are provided
     */
    @PostMapping(value = {"/AddCustomer"})
    @ResponseStatus(HttpStatus.CREATED)
//    public void AddCustomer(@Validated @RequestBody Customer customer) throws AdminException {
//        adminService.AddCustomer(customer);
//    }
    public ResponseEntity<?> AddCustomer
            (@RequestHeader("Authorization") String jwt,@Validated @RequestBody Customer customer)
            throws AdminException, SignatureException {
        return new ResponseEntity<>(adminService.AddCustomer(customer), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }

    /**
     * Update a Company
     * @param company company object with details to be updated
     * @throws AdminException If we get any exception.  Details are provided
     */
    @PutMapping("/UpdateCompany")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void UpdateCompany(@RequestBody Company company) throws AdminException, LoginException {
//        adminService.UpdateCompany(company);
//    }
    public ResponseEntity<?> UpdateCompany
            (@RequestHeader("Authorization") String jwt,@RequestBody Company company)
            throws AdminException, LoginException, SignatureException {
        //System.out.println("Request Reached BackEnd controller");
        //System.out.println(company);
        return new ResponseEntity<>(adminService.UpdateCompany(company),jwtUtil.getHeaders(jwt), HttpStatus.NO_CONTENT);
    }

    /**
     * Update a customer
     * @param customer customer object with details to be updated
     * @throws AdminException If we get any exception.  Details are provided
     */
    @PutMapping("/UpdateCustomer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void UpdateCustomer(@RequestBody Customer customer) throws AdminException, LoginException {
//        adminService.UpdateCustomer(customer);
//    }
    public ResponseEntity<?> UpdateCustomer
            (@RequestHeader("Authorization") String jwt,@RequestBody Customer customer)
            throws AdminException, LoginException, SignatureException {
        return new ResponseEntity<>(adminService.UpdateCustomer(customer),jwtUtil.getHeaders(jwt), HttpStatus.NO_CONTENT);
    }

    /**
     * Get One Company
     * @param id Id belonging to the company requested
     * @return company object with the requested company details
     * @throws AdminException  If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCompany/{id}")
    @ResponseStatus(HttpStatus.OK)
//    public Company GetOneCompany(@PathVariable int id) throws AdminException {
//        return adminService.GetOneCompany(id);
//    }
    public ResponseEntity<?> GetOneCompany
            (@RequestHeader("Authorization") String jwt,@PathVariable int id)
            throws AdminException, SignatureException {
        return new ResponseEntity<>(adminService.GetOneCompany(id),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get one customer
     * @param id Id belonging to the customer requested
     * @return customer object with the requested customer details
     * @throws AdminException If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
//    public Customer GetOneCustomer(@PathVariable int id) throws AdminException {
//        return adminService.GetOneCustomer(id);
//    }
    public ResponseEntity<?> GetOneCustomer
            (@RequestHeader("Authorization") String jwt, @PathVariable int id)
            throws AdminException, SignatureException {
        return new ResponseEntity<>(adminService.GetOneCustomer(id),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Delete a Company
     * @param id Id belonging to the company to be deleted
     * @throws AdminException If we get any exception.  Details are provided
     */
    @DeleteMapping("/DeleteCompany/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void DeleteCompany(@PathVariable int id) throws AdminException {
//        adminService.DeleteCompanyCoupons(id);
//        adminService.DeleteCompany(id);
//    }
    public ResponseEntity<?> DeleteCompany
            (@RequestHeader("Authorization") String jwt, @PathVariable int id)
            throws AdminException, SignatureException {
        adminService.DeleteCompanyCoupons(id);
        return new ResponseEntity<>(adminService.DeleteCompany(id),jwtUtil.getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    /**
     * Delete a customer
     * @param id Id belonging to the customer to be deleted
     * @throws AdminException  If we get any exception.  Details are provided
     */
    @DeleteMapping("/DeleteCustomer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void DeleteCustomer(@PathVariable int id) throws AdminException {
//        adminService.DeleteCustomer(id);
//    }
    public ResponseEntity<?> DeleteCustomer
            (@RequestHeader("Authorization") String jwt, @PathVariable int id)
            throws AdminException, SignatureException {
        return new ResponseEntity<>(adminService.DeleteCustomer(id),jwtUtil.getHeaders(jwt),HttpStatus.ACCEPTED);
    }


}
