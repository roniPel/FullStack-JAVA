package Test;

import Beans.Category;
import Beans.Coupon;
import ErrorHandling.CouponSystemException;
import Facade.CompanyFacade;
import Utils.DateFactory;
import Utils.FactoryUtils;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Company Methods class - contains methods used for testing Company user
 */
public class Methods_Company extends Methods{
    /**
     * Company Method - Add Coupon
     * @param companyFacade used to run method
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    public void AddCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        int companyId = companyFacade.GetCompanyDetails().getId();
        System.out.println("*** Method: Add Coupon ***");
        // Create new coupon
        Category category = Category.GetRandomCategory();
        String title = "CompanyAddCouponTitle"+GetrandInt(100);
        String description = "CompanyAddCouponDescription";
        LocalDate startDate = DateFactory.getLocalDate(false);
        LocalDate endDate = DateFactory.getLocalDate(true);
        double price = FactoryUtils.round(Math.random()*(maxPrice),2);
        int amount = GetrandInt(50);
        String image = "Image"+GetrandInt(10);

        Coupon coupon = new Coupon(150,companyId, category,title,description,
                        startDate,endDate, amount,price,image);
        // Add coupon to DB
        System.out.println("Coupon to add: ");
        System.out.print(coupon);
        System.out.println("Added Coupon? "+
                companyFacade.AddCoupon(coupon));
        System.out.println();
    }

    /**
     * Company Method - Update Coupon
     * @param companyFacade used to run method
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    public void UpdateCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Coupon ***");
        // Get all company coupons
        ArrayList<Coupon> coupons = companyFacade.GetAllCompanyCoupons();
        // Select a random coupon for update
        int updateCouponId = GetRandIdFromCouponsArray(coupons);
        Coupon updatedCoupon = null;
        for (Coupon coupon: coupons){
            if(coupon.getId() == updateCouponId){
                updatedCoupon = coupon;
                break;
            }
        }
        // Update fields
        updatedCoupon.setTitle("CompanyUpdatedTitle"+GetrandInt(100));
        updatedCoupon.setDescription("CompanyUpdatedDescription");
        updatedCoupon.setAmount(GetrandInt(50));
        updatedCoupon.setCategory(Category.GetRandomCategory());
        updatedCoupon.setImage("CompanyUpdatedImage");
        updatedCoupon.setStartDate(DateFactory.getLocalDate(false));
        updatedCoupon.setEndDate(DateFactory.getLocalDate(true));
        // Update coupon in DB
        System.out.println("Coupon to update: ");
        System.out.print(updatedCoupon);
        System.out.println("Updated Coupon? "+
                companyFacade.UpdateCoupon(updatedCoupon));
        System.out.println();
    }

    /**
     * Company Method - Delete Coupon
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public void DeleteCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Coupon ***");
        // Get all company coupons
        ArrayList<Coupon> coupons = companyFacade.GetAllCompanyCoupons();
        // Select random coupon ID for delete
        int delCouponId = GetRandIdFromCouponsArray(coupons);
        // Delete coupon in DB
        System.out.println("Coupon to delete: ");
        coupons.forEach((coupon -> {
            if(coupon.getId() == delCouponId){
                System.out.print(coupon);
                return;
            }
        }));
        System.out.println("Deleted Coupon? "+
                companyFacade.DeleteCoupon(delCouponId) );
        System.out.println();
    }

    /**
     * Company Method - Get company Coupons
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
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
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public void GetCompanyCouponsByCategory(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Company Coupons by Category ***");
        Category category = Category.GetRandomCategory();
        // Get company coupons by category
        ArrayList<Coupon> coupons = companyFacade.GetCompanyCouponsByCategory(category);
        // Display coupons
        System.out.println("Company's Coupons by Category '"+category+"': ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by max price
     * @param companyFacade used to run method
     * @throws CouponSystemException If we get any exception.  Details are provided
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
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public void GetCompanyDetails(CompanyFacade companyFacade) throws CouponSystemException {
        System.out.println("*** Method: Get Company Details ***");
        System.out.println("The logged on company details are: ");
        System.out.println(companyFacade.GetCompanyDetails());
        System.out.println();
    }
}
