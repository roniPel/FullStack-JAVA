package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Customer Test Methods Class - Used for Testing all Customer user functionalities via RestTemplate
 */
@Component
public class CustomerTestMethods_Rest extends TestMethods {
    @Autowired
    private RestTemplate restTemplate;

    //Todo - write test methods

    /**
     * Customer Method - Get Customer Details
     */
    public void GetCustomerDetails() {
    }

    /**
     * Customer Method - Purchase Coupon
     */
    public void PurchaseCoupon() {
    }

    /**
     * Customer Method - Get Customer Coupons
     */
    public void GetCustomerCoupons() {
    }

    /**
     * Customer Method - Get Customer Coupons by Category
     */
    public void GetCustomerCouponsByCategory() {
    }

    /**
     * Customer Method - Get Customer Coupons by Max Price
     */
    public void GetCustomerCouponsByMaxPrice() {
    }

}
