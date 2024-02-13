package Test;

import Beans.Category;
import Beans.Coupon;
import ErrorHandling.CouponSystemException;
import Facade.CompanyFacade;
import Utils.DateFactory;
import Utils.FactoryUtils;

import java.util.ArrayList;

public class Methods_Company extends Methods{
    /**
     * Company Method - Add Coupon
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void AddCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        int companyId = companyFacade.GetCompanyDetails().getId();
        System.out.println("*** Method: Add Coupon ***");
        // Create new coupon
        Coupon coupon = new Coupon(150,companyId, Category.GetRandomCategory(),
                "CompanyAddCouponTitle"+GetrandInt(100),"CompanyAddCouponDescription",
                DateFactory.getLocalDate(false),DateFactory.getLocalDate(true),
                GetrandInt(50),Math.random()*(maxPrice),"Image"+GetrandInt(10));
        // Add coupon to DB
        System.out.println("Added Coupon: "+
                companyFacade.AddCoupon(coupon));
        System.out.println();
    }

    /**
     * Company Method - Update Coupon
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void UpdateCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Coupon ***");
        // Get all company coupons
        ArrayList<Coupon> coupons = companyFacade.GetAllCompanyCoupons();
        // Select a random coupon for update
        int updateCouponId = GetrandInt( coupons.size() );
        Coupon updatedCoupon = coupons.get(updateCouponId);
        // Update fields
        updatedCoupon.setTitle("CompanyUpdatedTitle"+GetrandInt(10));
        updatedCoupon.setDescription("CompanyUpdatedDescription");
        updatedCoupon.setAmount(GetrandInt(50));
        updatedCoupon.setCategory(Category.GetRandomCategory());
        updatedCoupon.setImage("CompanyUpdatedImage");
        updatedCoupon.setStartDate(DateFactory.getLocalDate(false));
        updatedCoupon.setEndDate(DateFactory.getLocalDate(true));
        // Update coupon in DB
        System.out.println("Updated Coupon: "+
                companyFacade.UpdateCoupon(updatedCoupon));
        System.out.println();
    }

    /**
     * Company Method - Delete Coupon
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void DeleteCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Coupon ***");
        // Get all company coupons
        ArrayList<Coupon> coupons = companyFacade.GetAllCompanyCoupons();
        // Select random coupon ID for delete
        int delCouponId = GetrandInt( coupons.size() );
        // Delete coupon in DB
        System.out.println("Deleted Coupon: "+
                companyFacade.DeleteCoupon(delCouponId) );
        System.out.println();
    }

    /**
     * Company Method - Get company Coupons
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCompanyCoupons(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Company Coupons ***");
        // Get all company coupons
        ArrayList<Coupon> coupons = companyFacade.GetAllCompanyCoupons();
        // Display coupons
        System.out.println("Company's Coupons: ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by Category
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCompanyCouponsByCategory(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Company Coupons by Category ***");
        Category category = Category.GetRandomCategory();
        // Get company coupons by category
        ArrayList<Coupon> coupons = companyFacade.GetCompanyCouponsByCategory(category);
        // Display coupons
        System.out.println("Company's Coupons by Category "+category+": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by max price
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCompanyCouponsByMaxPrice(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Company Coupons by Max Price ***");
        double price = Math.random()*maxPrice;
        // Get company coupons by max price
        ArrayList<Coupon> coupons = companyFacade.GetCompanyCouponsByPrice(price);
        // Display coupons
        System.out.println("Company's Coupons by Max Price "+ FactoryUtils.beautifyPrice(price) +": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Details
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public void GetCompanyDetails(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Company Details ***");
        System.out.println(companyFacade.GetCompanyDetails());
    }
}
