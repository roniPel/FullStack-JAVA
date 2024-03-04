package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = {"/GetCustomerDetails"})
    @ResponseStatus(HttpStatus.OK)
    public Customer GetCustomerDetails() throws CustomerException {
        return customerService.GetCustomerDetails();
    }

    @PostMapping(value = "/PurchaseCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    public void PurchaseCoupon(@Validated @RequestBody Coupon coupon) throws CustomerException{
        customerService.PurchaseCoupon(coupon);
    }

    @GetMapping(value = {"/GetCustomerCoupons"})
    public List<Coupon> GetCustomerCoupons(){
        return customerService.GetCustomerCoupons();
    }

    @GetMapping("/GetCustomerCouponsByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCustomerCouponsByCategory(@PathVariable Category category) throws CustomerException {
        return customerService.GetCustomerCouponsByCategory(category);
    }

    @GetMapping("/GetCustomerCouponsByMaxPrice/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCustomerCouponsByMaxPrice(@PathVariable double maxPrice) throws CustomerException {
        return customerService.GetCustomerCouponsByMaxPrice(maxPrice);
    }
}
