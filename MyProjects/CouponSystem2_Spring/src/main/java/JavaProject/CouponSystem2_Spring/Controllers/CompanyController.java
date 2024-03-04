package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CouponSystem_Company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Company GetCompanyDetails() throws CompanyException {
        return companyService.GetCompanyDetails();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCoupon(@Validated @RequestBody Coupon coupon) throws CompanyException{
        companyService.AddCoupon(coupon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateCoupon(@PathVariable int id,@RequestBody Coupon coupon) throws CompanyException {
        companyService.AddCoupon(coupon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteCoupon(@PathVariable int id) throws CompanyException {
        companyService.DeleteCoupon(id);
    }
    @GetMapping
    public List<Coupon> GetCompanyCoupons(){
        return companyService.GetCompanyCoupons();
    }

    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCompanyCouponsByCategory(@PathVariable Category category) throws CompanyException {
        return companyService.GetCompanyCouponsByCategory(category);
    }

    @GetMapping("/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCompanyCouponsByMaxPrice(@PathVariable double maxPrice) throws CompanyException {
        return companyService.GetCompanyCouponsByMaxPrice(maxPrice);
    }
}
