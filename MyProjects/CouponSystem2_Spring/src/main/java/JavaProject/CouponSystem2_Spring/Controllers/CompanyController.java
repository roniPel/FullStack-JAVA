package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for Company User
 */
@Validated
@RestController
@RequestMapping("api/Company")
@RequiredArgsConstructor
public class CompanyController extends ClientController{
    private final CompanyService companyService;

    //Todo - write 'login' method - part 3
    @Override
    boolean login(String email, String password) {
        return false;
    }
    /**
     * Gets a company (according to the company ID belonging to the company logged on)
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CompanyException If we get any exception.  Details are provided
     */
    @GetMapping(value = {"/GetCompanyDetails"})
    @ResponseStatus(HttpStatus.OK)
    public Company GetCompanyDetails() throws CompanyException {
        return companyService.GetCompanyDetails();
    }

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    @PostMapping(value = "/AddCoupon")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCoupon(@Validated @RequestBody Coupon coupon) throws CompanyException{
        companyService.AddCoupon(coupon);
    }

    /**
     * Update Coupon in DB - based on the details listed in the param
     * @param coupon a 'Coupon' object used to update an object in the DB
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    @PutMapping("/UpdateCoupon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateCoupon(@RequestBody Coupon coupon) throws CompanyException {
        companyService.AddCoupon(coupon);
    }

    /**
     * Deletes a Coupon in DB - based on the details listed in the param
     * @param id the ID of the coupon to be deleted in the DB
     * @throws CompanyException If we get any exception.  Details are provided
     */
    @DeleteMapping("/DeleteCoupon/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteCoupon(@PathVariable int id) throws CompanyException {
        companyService.DeleteCoupon(id);
    }

    /**
     * Get all the coupons listed in DB for a specific company
     * @return coupon List if succeeded, null if no coupons were found.
     */
    @GetMapping(value = {"/GetCompanyCoupons"})
    public List<Coupon> GetCompanyCoupons(){
        return companyService.GetCompanyCoupons();
    }

    /**
     * Get all the coupons listed in DB for the logged on company belonging to a specific category
     * @param category - category of coupons to add to coupon list
     * @return coupon List if succeeded, null if no coupons matching category were found.
     */
    @GetMapping("/GetCompanyCouponsByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCompanyCouponsByCategory(@PathVariable Category category) {
        return companyService.GetCompanyCouponsByCategory(category);
    }

    /**
     * Get all the coupons listed in DB for the logged on company up to a max price
     * @param maxPrice - maximum price of coupons to add to coupon list
     * @return coupon List if succeeded, null if no coupons matching max price were found.
     */
    @GetMapping("/GetCompanyCouponsByMaxPrice/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> GetCompanyCouponsByMaxPrice(@PathVariable double maxPrice) {
        return companyService.GetCompanyCouponsByMaxPrice(maxPrice);
    }

    //Todo - Consider deleting 'GetOneCoupon' below - if not needed

    /**
     * Get one coupon
     * @param id id belonging to the coupon requested
     * @return coupon object with the requested coupon details
     * @throws CompanyException If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCoupon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Coupon GetOneCoupon(@PathVariable int id) throws CompanyException{
        return companyService.GetOneCoupon(id);
    }


}
