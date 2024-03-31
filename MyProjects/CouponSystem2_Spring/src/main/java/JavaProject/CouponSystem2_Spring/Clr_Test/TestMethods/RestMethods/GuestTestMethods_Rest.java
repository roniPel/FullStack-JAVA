package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Guest Test Methods Class - Used for Testing all Guest user functionalities via RestTemplate
 */
@Component
@RequiredArgsConstructor
public class GuestTestMethods_Rest extends TestMethods {
    private final RestTemplate restTemplate;

    /**
     * Customer Method - Purchase Coupon
     */
    public void PurchaseCoupon() {
        System.out.println("*** Method: Purchase Coupon ***");
        // Select random coupon from non-customer coupons list
        List<Coupon> nonCustomerCoupons = GetListOfAllCoupons();
        int couponForPurchaseId = GetRandIdFromList(nonCustomerCoupons);
        Coupon couponForPurchase = restTemplate.getForObject
                ("http://localhost:8080/Guest/GetOneCoupon/"+couponForPurchaseId, Coupon.class);;

        // Add coupon to DB
        System.out.println("Coupon for purchase: ");
        System.out.println(couponForPurchase);
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/Guest/PurchaseCoupon",couponForPurchase,String.class);
        System.out.print("Purchased Coupon? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }

    /**
     * Creates a list of all coupons (convert array to list)
     * @return List of all coupons
     */
    private List<Coupon> GetListOfAllCoupons() {
        Coupon[] coupons = restTemplate.getForObject
                ("http://localhost:8080/Guest/GetAllCoupons", Coupon[].class);
        return Arrays.stream(coupons).toList();
    }

    public void GetAllCoupons() {
        System.out.println("*** Method: Get All Coupons ***");
        List<Coupon> allCoupons = GetListOfAllCoupons();
        System.out.println("All coupons: ");
        allCoupons.forEach(System.out::println);
        System.out.println();
    }
}
