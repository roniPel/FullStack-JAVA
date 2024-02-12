package Test;

import Beans.Category;
import Beans.Coupon;
import ErrorHandling.CouponSystemException;
import Facade.CompanyFacade;
import Utils.DateFactory;

import java.util.ArrayList;

public class Methods_Company extends Methods{
    public void AddCoupon(CompanyFacade companyFacade) throws CouponSystemException {
        int companyId = companyFacade.GetCompanyDetails().getId();
        System.out.println("*** Method: Add Coupon ***");
        // Create new coupon
        Coupon coupon = new Coupon(150,companyId, Category.GetRandomCategory(),
                "CompanyAddCouponTitle"+GetrandInt(100),"CompanyAddCouponDescription",
                DateFactory.getLocalDate(false),DateFactory.getLocalDate(true),
                GetrandInt(50),Math.random()*(2000.00),"Image"+GetrandInt(10));
        // Add coupon to DB
        System.out.println("Added Coupon: "+
                companyFacade.AddCoupon(coupon));
        System.out.println();
    }
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

}
