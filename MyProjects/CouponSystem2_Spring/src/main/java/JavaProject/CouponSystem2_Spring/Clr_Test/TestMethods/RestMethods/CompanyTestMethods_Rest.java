package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompanyTestMethods_Rest extends TestMethods {
    @Autowired
    private RestTemplate restTemplate;

    //Todo - write test methods
    public void GetCompanyDetails() {
    }

    public void AddCoupon() {
    }

    public void UpdateCoupon() {
    }

    public void DeleteCoupon() {
    }

    public void GetCompanyCoupons() {
    }

    public void GetCompanyCouponsByCategory() {
    }

    public void GetCompanyCouponsByMaxPrice() {
    }

}
