package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Company Test Methods Class - Used for Testing all Company user functionalities via RestTemplate
 */
@Component
public class CompanyTestMethods_Rest extends TestMethods {
    @Autowired
    private RestTemplate restTemplate;

    //Todo - write test methods

    /**
     * Company Method - Get Company Details
     */
    public void GetCompanyDetails() {
    }

    /**
     * Company Method - Add Coupon
     */
    public void AddCoupon() {
    }

    /**
     * Company Method - Update Coupon
     */
    public void UpdateCoupon() {
    }

    /**
     * Company Method - Delete Coupon
     */
    public void DeleteCoupon() {
    }

    /**
     * Company Method - Get Company Coupons
     */
    public void GetCompanyCoupons() {
    }

    /**
     * Company Method - Get Company Coupons by category
     */
    public void GetCompanyCouponsByCategory() {
    }

    /**
     * Company Method - Get Company Coupons by max Price
     */
    public void GetCompanyCouponsByMaxPrice() {
    }

}
