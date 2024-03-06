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
/**
 * Controller for Customer User
 */
@Validated
@RestController
@RequestMapping("api/Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * Gets a customer (according to the customer ID belonging to the customer logged on)
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @GetMapping(value = {"/GetCustomerDetails"})
    @ResponseStatus(HttpStatus.OK)
    public Customer GetCustomerDetails() throws CustomerException {
        return customerService.GetCustomerDetails();
    }

    /**
     * Adds a coupon purchase in the DB for the logged on customer
     * @param coupon 'coupon' object to purchase
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @PostMapping(value = "/PurchaseCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    public void PurchaseCoupon(@Validated @RequestBody Coupon coupon) throws CustomerException{
        customerService.PurchaseCoupon(coupon);
    }

    /**
     * Get all the coupons listed in DB for the customer logged on
     * @return coupons ArrayList if succeeded, null if failed.
     */
    @GetMapping(value = {"/GetCustomerCoupons"})
    public List<Coupon> GetCustomerCoupons(){
        return customerService.GetCustomerCoupons();
    }

    /**
     * Get all the coupons listed in DB for the logged on customer belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching category were found.
     */
    @GetMapping("/GetCustomerCouponsByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCustomerCouponsByCategory(@PathVariable Category category) {
        return customerService.GetCustomerCouponsByCategory(category);
    }

    /**
     * Get all the coupons listed in DB for the logged on customer up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching max price were found.
     */
    @GetMapping("/GetCustomerCouponsByMaxPrice/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCustomerCouponsByMaxPrice(@PathVariable double maxPrice) {
        return customerService.GetCustomerCouponsByMaxPrice(maxPrice);
    }
}
