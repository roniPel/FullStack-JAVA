package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        System.out.println("*** Method: Purchase Coupon ***");
        // Select random coupon from non-customer coupons list
        List<Coupon> nonCustomerCoupons = GetListOfAllCoupons();
        nonCustomerCoupons.removeAll(GetListCustomerCoupons());
        int couponForPurchaseId = GetRandIdFromList(nonCustomerCoupons);
        Coupon couponForPurchase = restTemplate.getForObject
                ("http://localhost:8080/api/Customer/GetOneCoupon/"+couponForPurchaseId, Coupon.class);;

        // Add coupon to DB
        System.out.println("Coupon for purchase: ");
        System.out.println(couponForPurchase);
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/api/Customer/PurchaseCoupon",couponForPurchase,String.class);
        System.out.print("Purchased Coupon? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
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

    /**
     * Creates a list of all coupons (convert array to list)
     * @return List of all coupons
     */
    private List<Coupon> GetListOfAllCoupons() {
        Coupon[] coupons = restTemplate.getForObject
                ("http://localhost:8080/api/Customer/GetAllCoupons", Coupon[].class);
        return Arrays.stream(coupons).toList();
    }

    /**
     * Creates a list of customer coupons (convert array to list)
     * @return List of customer coupons
     */
    private List<Coupon> GetListCustomerCoupons() {
        Coupon[] coupons = restTemplate.getForObject
                ("http://localhost:8080/api/Customer/GetCustomerCoupons", Coupon[].class);
        return Arrays.stream(coupons).toList();
    }

}
