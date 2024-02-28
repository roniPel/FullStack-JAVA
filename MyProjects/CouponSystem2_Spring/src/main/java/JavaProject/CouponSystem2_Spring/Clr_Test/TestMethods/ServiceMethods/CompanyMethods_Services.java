package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class CompanyMethods_Services extends TestMethods {
    /**
     * Company Method - Get Company Details
     * @param companyService used to run method
     * @throws CompanyException If we get any exception.  Details are provided
     */
    public void GetCompanyDetails(CompanyService companyService) throws CompanyException {
        System.out.println("*** Method: Get Company Details ***");
        System.out.println("The logged on company details are: ");
        System.out.println(companyService.GetCompanyDetails());
        System.out.println();
    }

    /**
     * Company Method - Add Coupon
     * @param companyService used to run method
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    public void AddCoupon(CompanyService companyService) throws CompanyException {
        System.out.println("*** Method: Add Coupon ***");
        int companyId = companyService.GetCompanyDetails().getA_id();
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

        Coupon coupon = Coupon.builder()
                .a_id(50)
                .b_company_id(companyId)
                .c_category(category)
                .d_title(title)
                .e_description(description)
                .f_startDate(startDate)
                .g_endDate(endDate)
                .h_amount(amount)
                .i_price(price)
                .j_image(image)
                .build();
        // Add coupon to DB
        System.out.println("Coupon to add: ");
        System.out.print(coupon);
        System.out.println("Added Coupon? "+
                companyService.AddCoupon(coupon));
        System.out.println();
    }

    /**
     * Company Method - Update Coupon
     * @param companyService used to run method
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    public void UpdateCoupon(CompanyService companyService) throws CompanyException {
        System.out.println("*** Method: Update Coupon ***");
        // Select a random coupon for update
        int updateCouponId = GetRandIdFromList(companyService.GetCompanyCoupons());
        Coupon updatedCoupon = companyService.GetCompanyCoupons().get(updateCouponId);
        // Update fields
        updatedCoupon.setD_title("CompanyUpdatedTitle"+GetrandInt(100));
        updatedCoupon.setE_description("CompanyUpdatedDescription");
        updatedCoupon.setH_amount(GetrandInt(50));
        updatedCoupon.setC_category(Category.GetRandomCategory());
        updatedCoupon.setJ_image("CompanyUpdatedImage");
        updatedCoupon.setF_startDate(DateFactory.getLocalDate(false));
        updatedCoupon.setG_endDate(DateFactory.getLocalDate(true));
        // Update coupon in DB
        System.out.println("Coupon to update: ");
        System.out.print(updatedCoupon);
        System.out.println("Updated Coupon? "+
                companyService.UpdateCoupon(updatedCoupon));
        System.out.println();
    }

    /**
     * Company Method - Get company Coupons
     * @param companyService used to run method
     */
    public void GetCompanyCoupons(CompanyService companyService) {
        System.out.println("*** Method: Get Company Coupons ***");
        // Get all company coupons
        List<Coupon> coupons = companyService.GetCompanyCoupons();
        // Display coupons
        System.out.println("Company's Coupons: ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by Category
     * @param companyService used to run method
     */
    public void GetCompanyCouponsByCategory(CompanyService companyService) {
        System.out.println("*** Method: Get Company Coupons by Category ***");
        Category category = Category.GetRandomCategory();
        // Get company coupons by category
        List<Coupon> coupons = companyService.GetCompanyCouponsByCategory(category);
        // Display coupons
        System.out.println("Company's Coupons by Category '"+category+"': ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by max price
     * @param companyService used to run method
     */
    public void GetCompanyCouponsByMaxPrice(CompanyService companyService) {
        System.out.println("*** Method: Get Company Coupons by Max Price ***");
        double price = Math.random()*maxPrice;
        // Get company coupons by max price
        List<Coupon> coupons = companyService.GetCompanyCouponsByMaxPrice(price);
        // Display coupons
        System.out.println("Company's Coupons by Max Price "+ FactoryUtils.beautifyPrice(price) +": ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Delete Coupon
     * @param companyService used to run method
     * @throws CompanyException If we get any exception.  Details are provided
     */
    public void DeleteCoupon(CompanyService companyService) throws CompanyException {
        System.out.println("*** Method: Delete Coupon ***");
        // Select random coupon ID for delete
        int delCouponId = GetRandIdFromList(companyService.GetCompanyCoupons());
        Coupon deleteCoupon = companyService.GetCompanyCoupons().get(delCouponId);
        // Delete coupon in DB
        System.out.println("Coupon to delete: ");
        System.out.println(deleteCoupon);
        System.out.println("Deleted Coupon? "+
                companyService.DeleteCoupon(delCouponId) );
        System.out.println();
    }
}
