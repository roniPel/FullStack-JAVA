package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.*;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;
/**
 * Controller for Company User
 */
@Validated
@RestController
@CrossOrigin()
@RequestMapping("/Company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWT jwtUtil;

    /**
     * Gets a company (according to the company ID belonging to the company logged on)
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CompanyException If we get any exception.  Details are provided
     */
    @GetMapping(value = {"/GetCompanyDetails"})
    @ResponseStatus(HttpStatus.OK)
//    public Company GetCompanyDetails() throws CompanyException {
//        return companyService.GetCompanyDetails();
//    }

    public ResponseEntity<?> GetCompanyDetails
            (@RequestHeader("Authorization") String jwt)
            throws SignatureException, CompanyException {
        return new ResponseEntity<>(companyService.GetCompanyDetails(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    @PostMapping(value = "/AddCoupon")
    @ResponseStatus(HttpStatus.CREATED)
//    public int AddCoupon(@Validated @RequestBody Coupon coupon) throws CompanyException{
//        return companyService.AddCoupon(coupon);
//    }

    public ResponseEntity<?> AddCoupon
            (@RequestHeader("Authorization") String jwt,@Validated @RequestBody Coupon coupon)
            throws SignatureException, CompanyException {
        return new ResponseEntity<>(companyService.AddCoupon(coupon), jwtUtil.getHeaders(jwt), HttpStatus.OK);
    }

    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    @PutMapping("/UpdateCoupon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void UpdateCoupon(@RequestBody Coupon coupon) throws CompanyException {
//        companyService.UpdateCoupon(coupon);
//    }

    public ResponseEntity<?> UpdateCoupon
            (@RequestHeader("Authorization") String jwt,@RequestBody Coupon coupon)
            throws SignatureException, CompanyException {
        return new ResponseEntity<>(companyService.UpdateCoupon(coupon),jwtUtil.getHeaders(jwt), HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param id the ID of the coupon to be deleted in the DB
     * @throws CompanyException If we get any exception.  Details are provided
     */
    @DeleteMapping("/DeleteCoupon/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void DeleteCoupon(@PathVariable int id) throws CompanyException {
//        companyService.DeleteCoupon(id);
//    }

    public ResponseEntity<?> DeleteCoupon
            (@RequestHeader("Authorization") String jwt, @PathVariable int id)
            throws SignatureException, CompanyException {
        return new ResponseEntity<>(companyService.DeleteCoupon(id),jwtUtil.getHeaders(jwt),HttpStatus.ACCEPTED);
    }

    /**
     * Get all the coupons listed in DB for a specific company
     * @return coupon List if succeeded, null if no coupons were found.
     */
    @GetMapping(value = {"/GetCompanyCoupons"})
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetCompanyCoupons(){
//        return companyService.GetCompanyCoupons();
//    }

    public ResponseEntity<?> GetCompanyCoupons (@RequestHeader("Authorization") String jwt)
            throws SignatureException {
        return new ResponseEntity<>(companyService.GetCompanyCoupons(),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get all the coupons listed in DB for the logged on company belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupon List if succeeded, null if no coupons matching category were found.
     */
    @GetMapping("/GetCompanyCouponsByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetCompanyCouponsByCategory(@PathVariable Category category) {
//        return companyService.GetCompanyCouponsByCategory(category);
//    }

    public ResponseEntity<?> GetCompanyCouponsByCategory
            (@RequestHeader("Authorization") String jwt,@PathVariable Category category)
            throws SignatureException {
        return new ResponseEntity<>(companyService.GetCompanyCouponsByCategory(category),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get all the coupons listed in DB for the logged on company up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupon List if succeeded, null if no coupons matching max price were found.
     */
    @GetMapping("/GetCompanyCouponsByMaxPrice/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
//    public List<Coupon> GetCompanyCouponsByMaxPrice(@PathVariable double maxPrice) {
//        return companyService.GetCompanyCouponsByMaxPrice(maxPrice);
//    }

    public ResponseEntity<?> GetCompanyCouponsByMaxPrice
            (@RequestHeader("Authorization") String jwt,@PathVariable double maxPrice)
            throws SignatureException {
        return new ResponseEntity<>(companyService.GetCompanyCouponsByMaxPrice(maxPrice),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

    /**
     * Get one coupon
     * @param id id belonging to the coupon requested
     * @return coupon object with the requested coupon details
     * @throws CompanyException If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCoupon/{id}")
    @ResponseStatus(HttpStatus.OK)
//    public Coupon GetOneCoupon(@PathVariable int id) throws CompanyException{
//        return companyService.GetOneCoupon(id);
//    }
    public ResponseEntity<?> GetOneCoupon
            (@RequestHeader("Authorization") String jwt, @PathVariable int id)
            throws AdminException, SignatureException, CompanyException {
        return new ResponseEntity<>(companyService.GetOneCoupon(id),jwtUtil.getHeaders(jwt),HttpStatus.OK);
    }

}
