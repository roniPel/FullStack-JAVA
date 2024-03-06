package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

/**
 * Company Test Methods Class - Used for Testing all Company user functionalities via services
 */
@Component
public class CompanyTestMethods_Services extends TestMethods {
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
        int companyId = companyService.GetCompanyDetails().getId();
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
                .id(50)
                .companyId(companyId)
                .category(category)
                .title(title)
                .description(description)
                .start_date(startDate)
                .end_date(endDate)
                .amount(amount)
                .price(price)
                .image(image)
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
        Coupon updatedCoupon = companyService.GetCompanyCoupons().stream()
                .filter( (coupon) -> coupon.getId()==updateCouponId)
                .findFirst()
                .orElseThrow(
                        () -> new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST)
                );
        // Update fields
        updatedCoupon.setTitle("CompUpdated");
        updatedCoupon.setDescription("CompUpdated"+GetrandInt(100));
        updatedCoupon.setAmount(GetrandInt(50));
        updatedCoupon.setCategory(Category.GetRandomCategory());
        updatedCoupon.setImage("CompUpdated");
        updatedCoupon.setStart_date(DateFactory.getLocalDate(false));
        updatedCoupon.setEnd_date(DateFactory.getLocalDate(true));
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
        Coupon deleteCoupon = companyService.GetCompanyCoupons().stream()
                .filter( (coupon) -> coupon.getId()==delCouponId)
                .findFirst()
                .orElseThrow(
                        () -> new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST)
                );
        // Delete coupon in DB
        System.out.println("Coupon to delete: ");
        System.out.println(deleteCoupon);
        System.out.println("Deleted Coupon? "+
                companyService.DeleteCoupon(delCouponId) );
        System.out.println();
    }
}
