package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import JavaProject.CouponSystem2_Spring.Services.GuestService.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Guest User
 */
@Validated
@RestController
@RequestMapping("api/Guest")
@RequiredArgsConstructor
public class GuestController extends ClientController{
    private final GuestService guestService;

    //Todo - write 'login' method - part 3
    @Override
    boolean login(String email, String password) {
        return false;
    }

//    /**
//     * Adds a coupon purchase in the DB for the logged on customer
//     * @param coupon 'coupon' object to purchase
//     * @throws GuestException If we get any exception.  Details are provided
//     */
//    @PostMapping(value = "/PurchaseCoupon")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void PurchaseCoupon(@Validated @RequestBody Coupon coupon) throws GuestException {
//        guestService.PurchaseCoupon(coupon);
//    }

//    /**
//     * Get one coupon
//     * @param id id belonging to the coupon requested
//     * @return coupon object with the requested coupon details
//     * @throws GuestException If we get any exception.  Details are provided
//     */
//    @GetMapping("/GetOneCoupon/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Coupon GetOneCoupon(@PathVariable int id) throws GuestException {
//        return guestService.GetCouponById(id);
//    }

    /**
     * Gets all coupons in DB
     * @return A list of all coupons in the DB
     */
    @GetMapping(value = {"/GetAllCoupons"})
    public List<Coupon> GetAllCoupons(){
        return guestService.GetAllCoupons();
    }
}
