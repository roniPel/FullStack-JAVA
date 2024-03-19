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

    //Todo - write methods for guest

    /**
     * Guest Method - Purchase Coupon
     * @param guestService used to run method
     * @throws GuestException If we get any exception.  Details are provided
     */
    public void PurchaseCoupon(GuestService guestService) throws GuestException {
        System.out.println("*** Method: Purchase Coupon ***");

        // Select random coupon from non-customer coupons list
        List<Coupon> allCoupons = guestService.GetAllCoupons();

        int couponForPurchaseId = GetRandIdFromList(allCoupons);
        Coupon couponForPurchase = guestService.GetCouponById(couponForPurchaseId);

        // Add coupon to DB
        System.out.println("Coupon for purchase: ");
        System.out.println(couponForPurchase);
        System.out.println("Purchased Coupon? "+
                guestService.PurchaseCoupon(couponForPurchase));
        System.out.println();
    }


}
