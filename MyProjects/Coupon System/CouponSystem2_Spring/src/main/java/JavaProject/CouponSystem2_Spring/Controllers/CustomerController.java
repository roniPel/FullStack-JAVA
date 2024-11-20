package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.*;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;
/**
 * Controller for Customer User
 */
@Validated
@CrossOrigin()
@RestController
@RequestMapping("/Customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final JWT jwtUtil;

    //Todo - insert JWT authentication check - part 3
    /**
     * Gets a customer (according to the customer ID belonging to the customer logged on)
     * @return a 'Customer' class item if succeeded, 'null' if failed or if no customer matches the requirements.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @GetMapping(value = {"/GetCustomerDetails"})
    @ResponseStatus(HttpStatus.OK)
//    public Customer GetCustomerDetails() throws CustomerException {
//        return customerService.GetCustomerDetails();
//    }

    public ResponseEntity<?> GetCustomerDetails
            (@RequestHeader("Authorization") String jwt)
            throws SignatureException, CustomerException {
        return new ResponseEntity<>(customerService.GetCustomerDetails(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Adds a coupon purchase in the DB for the logged on customer
     * @param coupon 'coupon' object to purchase
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @PostMapping(value = "/PurchaseCoupon")
    @ResponseStatus(HttpStatus.CREATED)
//    public void PurchaseCoupon(@Validated @RequestBody Coupon coupon) throws CustomerException{
//        customerService.PurchaseCoupon(coupon);
//    }

    public ResponseEntity<?> PurchaseCoupon
            (@RequestHeader("Authorization") String jwt,@Validated @RequestBody Coupon coupon)
            throws SignatureException, CustomerException {
        return new ResponseEntity<>(customerService.PurchaseCoupon(coupon), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }

    /**
     * Get all the coupons listed in DB for the customer logged on
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @GetMapping(value = {"/GetCustomerCoupons"})
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetCustomerCoupons() throws CustomerException {
//        return customerService.GetCustomerCoupons();
//    }

    public ResponseEntity<?> GetCustomerCoupons
            (@RequestHeader("Authorization") String jwt)
            throws SignatureException, CustomerException {
        return new ResponseEntity<>(customerService.GetCustomerCoupons(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }
    /**
     * Get all the coupons listed in DB for the logged on customer belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching category were found.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @GetMapping("/GetCustomerCouponsByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetCustomerCouponsByCategory(@PathVariable Category category) throws CustomerException {
//        return customerService.GetCustomerCouponsByCategory(category);
//    }

    public ResponseEntity<?> GetCustomerCouponsByCategory
            (@RequestHeader("Authorization") String jwt,@PathVariable Category category)
            throws SignatureException, CustomerException {
        return new ResponseEntity<>(customerService.GetCustomerCouponsByCategory(category),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get all the coupons listed in DB for the logged on customer up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupons ArrayList if succeeded, null if no coupons matching max price were found.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @GetMapping("/GetCustomerCouponsByMaxPrice/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetCustomerCouponsByMaxPrice(@PathVariable double maxPrice) throws CustomerException {
//        return customerService.GetCustomerCouponsByMaxPrice(maxPrice);
//    }

    public ResponseEntity<?> GetCustomerCouponsByMaxPrice
            (@RequestHeader("Authorization") String jwt,@PathVariable double maxPrice)
            throws SignatureException, CustomerException {
        return new ResponseEntity<>(customerService.GetCustomerCouponsByMaxPrice(maxPrice),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get one coupon
     * @param id id belonging to the coupon requested
     * @return coupon object with the requested coupon details
     * @throws CustomerException If we get any exception.  Details are provided
     * @throws CustomerException If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCoupon/{id}")
    @ResponseStatus(HttpStatus.OK)
//    public Coupon GetOneCoupon(@PathVariable int id) throws CustomerException {
//        return customerService.GetCouponById(id);
//    }

    public ResponseEntity<?> GetOneCoupon
            (@RequestHeader("Authorization") String jwt, @PathVariable int id)
            throws AdminException, SignatureException, CompanyException, CustomerException {
        return new ResponseEntity<>(customerService.GetCouponById(id),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Gets all coupons in DB
     * @return A list of all coupons in the DB
     */
    @GetMapping(value = {"/GetAllCoupons"})
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetAllCoupons(){
//        return customerService.GetAllCoupons();
//    }
    public ResponseEntity<?> GetAllCoupons (@RequestHeader("Authorization") String jwt)
            throws SignatureException {
        return new ResponseEntity<>(customerService.GetAllCoupons(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }


}
