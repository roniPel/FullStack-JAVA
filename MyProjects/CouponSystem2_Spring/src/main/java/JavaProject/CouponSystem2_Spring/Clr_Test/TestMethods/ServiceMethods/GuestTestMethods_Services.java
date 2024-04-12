package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import JavaProject.CouponSystem2_Spring.Services.GuestService.GuestService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Guest Test Methods Class - Used for Testing all Guest user functionalities via services
 */
@Component
public class GuestTestMethods_Services extends TestMethods {

    /**
     * Guest Method - Get All Coupons
     * @param guestService used to run method
     */
    public void GetAllCoupons(GuestService guestService) {
        System.out.println("*** Method: Get All Coupons ***");
        List<Coupon> allCoupons = guestService.GetAllCoupons();
        System.out.println("All coupons: ");
        allCoupons.forEach(System.out::println);
        System.out.println();
    }
}
