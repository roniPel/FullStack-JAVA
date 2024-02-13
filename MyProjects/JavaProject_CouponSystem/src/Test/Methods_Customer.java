package Test;

import Beans.Category;
import Beans.Coupon;
import ErrorHandling.CouponSystemException;
import Facade.CustomerFacade;
import Utils.DateFactory;
import Utils.FactoryUtils;

import java.util.ArrayList;

public class Methods_Customer extends Methods{

    /**
     * Customer Method - Purchase Coupon
     * @param customerFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void PurchaseCoupon(CustomerFacade customerFacade) throws CouponSystemException {
        int companyId = customerFacade.GetCustomerDetails().getId();
        System.out.println("*** Method: Purchase Coupon ***");
        // Create new coupon
        Coupon coupon = new Coupon(150,companyId, Category.GetRandomCategory(),
                "CustomerAddCouponTitle"+GetrandInt(100),"CustomerAddCouponDescription",
                DateFactory.getLocalDate(false),DateFactory.getLocalDate(true),
                GetrandInt(50),Math.random()*(maxPrice),"Image"+GetrandInt(10));
        // Add coupon to DB
        System.out.println("Purchased Coupon: "+
                customerFacade.PurchaseCoupon(coupon));
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Coupons
     * @param customerFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCustomerCoupons(CustomerFacade customerFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Customer Coupons ***");
        // Get all company coupons
        ArrayList<Coupon> coupons = customerFacade.GetAllCustomerCoupons();
        // Display coupons
        System.out.println("Customer's Coupons: ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Coupons by Category
     * @param customerFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCustomerCouponsByCategory(CustomerFacade customerFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Customer Coupons by Category ***");
        Category category = Category.GetRandomCategory();
        // Get customer coupons by category
        ArrayList<Coupon> coupons = customerFacade.GetCustomerCouponsByCategory(category);
        // Display coupons
        System.out.println("Customer's Coupons by Category "+category+": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Coupons by max price
     * @param customerFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCustomerCouponsByMaxPrice(CustomerFacade customerFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Customer Coupons by Max Price ***");
        double price = Math.random()*maxPrice;
        // Get company coupons by max price
        ArrayList<Coupon> coupons = customerFacade.GetCustomerCouponsByPrice(price);
        // Display coupons
        System.out.println("Customer's Coupons by Max Price "+ FactoryUtils.beautifyPrice(price) +": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Customer Method - Get Customer Details
     * @param customerFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCustomerDetails(CustomerFacade customerFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Customer Details ***");
        System.out.println(customerFacade.GetCustomerDetails());
    }
}