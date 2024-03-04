package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CouponSystem_Admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @GetMapping(value = {"/GetAllCompanies"})
    public List<Company> GetAllCompanies(){
        return adminService.GetAllCompanies();
    }

    @GetMapping(value = {"/GetAllCustomers"})
    public List<Customer> GetAllCustomers(){
        return adminService.GetAllCustomers();
    }

    @PostMapping(value = {"/AddCompany"})
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCompany(@Validated @RequestBody Company company) throws AdminException {
        adminService.AddCompany(company);
    }
    @PostMapping(value = {"/AddCustomer"})
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCustomer(@Validated @RequestBody Customer customer) throws AdminException {
        adminService.AddCustomer(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateCompany(@PathVariable int id,@RequestBody Company company) throws AdminException {
        adminService.UpdateCompany(company);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateCustomer(@PathVariable int id,@RequestBody Customer customer) throws AdminException {
        adminService.UpdateCustomer(customer);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company GetOneCompany(@PathVariable int id) throws AdminException {
        return adminService.GetOneCompany(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer GetOneCustomer(@PathVariable int id) throws AdminException {
        return adminService.GetOneCustomer(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteCompany(@PathVariable int id) throws AdminException {
        adminService.DeleteCompany(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteCustomer(@PathVariable int id) throws AdminException {
        adminService.DeleteCustomer(id);
    }
}
