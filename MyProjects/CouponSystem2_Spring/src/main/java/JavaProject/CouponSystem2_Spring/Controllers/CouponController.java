package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Guest User
 */
@Validated
@RestController
@RequestMapping("api/Coupon")
public class CouponController {
    @Autowired
    CustomerService customerService;

    //Todo - how to purchase coupons without user?
}
