package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Customer Test Methods Class - Used for Testing all Customer user functionalities via services
 */
@Component
public class CustomerTestMethods_Services extends TestMethods {
    /**
     * Customer Method - Purchase Coupon
     * @param customerService used to run method
     * @throws CustomerException If we get any exception.  Details are provided
     */
    public void PurchaseCoupon(CustomerService customerService) throws CustomerException {
        Customer loggedCustomer = customerService.GetCustomerDetails();
        System.out.println("*** Method: Purchase Coupon ***");

        // Select random coupon from non-customer coupons list
        List<Coupon> nonCustomerCoupons = customerService.GetAllCoupons();
        nonCustomerCoupons.removeAll(customerService.GetCustomerCoupons());

        int couponForPurchaseId = GetRandIdFromList(nonCustomerCoupons);
        Coupon couponForPurchase = customerService.GetCouponById(couponForPurchaseId);

        // Add coupon to DB
        System.out.println("Coupon for purchase: ");
        System.out.println(couponForPurchase);
        System.out.println("Purchased Coupon? "+
                customerService.PurchaseCoupon(couponForPurchase));
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Coupons
     * @param customerService used to run method
     */
    public void GetCustomerCoupons(CustomerService customerService)  {
        System.out.println("*** Method: Get Customer Coupons ***");
        // Get all company coupons
        List<Coupon> coupons = customerService.GetCustomerCoupons();
        // Display coupons
        System.out.println("Customer's Coupons: ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Coupons by Category
     * @param customerService used to run method
     */
    public void GetCustomerCouponsByCategory(CustomerService customerService) {
        System.out.println("*** Method: Get Customer Coupons by Category ***");
        Category category = Category.GetRandomCategory();
        // Get customer coupons by category
        List<Coupon> coupons = customerService.GetCustomerCouponsByCategory(category);
        // Display coupons
        System.out.println("Customer's Coupons by Category "+category+": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Coupons by max price
     * @param customerService used to run method
     */
    public void GetCustomerCouponsByMaxPrice(CustomerService customerService) {
        System.out.println("*** Method: Get Customer Coupons by Max Price ***");
        double price = Math.random()*maxPrice;
        // Get company coupons by max price
        List<Coupon> coupons = customerService.GetCustomerCouponsByMaxPrice(price);
        // Display coupons
        System.out.println("Customer's Coupons by Max Price "+ FactoryUtils.beautifyPrice(price) +": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Details
     * @param customerService used to run method
     * @throws CustomerException If we get any exception.  Details are provided
     */
    public void GetCustomerDetails(CustomerService customerService) throws CustomerException {
        System.out.println("*** Method: Get Customer Details ***");
        System.out.println("The logged on customer details are: ");
        System.out.println(customerService.GetCustomerDetails());
        System.out.println();
    }

}
