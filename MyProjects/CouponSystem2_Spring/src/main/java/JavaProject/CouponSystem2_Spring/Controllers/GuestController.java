package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Services.GuestService.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Guest User
 */
@Validated
@RestController
@CrossOrigin()
@RequestMapping("/Guest")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    /**
     * Get one coupon
     * @param id id belonging to the coupon requested
     * @return coupon object with the requested coupon details
     * @throws GuestException If we get any exception.  Details are provided
     */
    @GetMapping("/GetOneCoupon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Coupon GetOneCoupon(@PathVariable int id) throws GuestException {
        return guestService.GetCouponById(id);
    }

    /**
     * Gets all coupons in DB
     * @return A list of all coupons in the DB
     */
    @GetMapping(value = {"/GetAllCoupons"})
    public List<Coupon> GetAllCoupons(){
        return guestService.GetAllCoupons();
    }
}
