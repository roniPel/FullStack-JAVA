package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerTestMethods_Rest extends TestMethods {
    @Autowired
    private RestTemplate restTemplate;

    //Todo - write test methods

    public void GetCustomerDetails() {
    }

    public void PurchaseCoupon() {
    }

    public void GetCustomerCoupons() {
    }

    public void GetCustomerCouponsByCategory() {
    }

    public void GetCustomerCouponsByMaxPrice() {
    }

}
