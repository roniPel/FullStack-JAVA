package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.TestMethods;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Company Test Methods Class - Used for Testing all Company user functionalities via RestTemplate
 */
@Component
@RequiredArgsConstructor
public class CompanyTestMethods_Rest extends TestMethods {
    private final RestTemplate restTemplate;
    private int companyId;

    /**
     * Company Method - Get Company Details
     */
    public void GetCompanyDetails() {
        System.out.println("*** Method: Get Company Details ***");
        System.out.println("The logged on company details are: ");
        Company company = restTemplate.getForObject
                ("http://localhost:8080/Company/GetCompanyDetails",Company.class);
        System.out.println(company);
        System.out.println();
    }

    /**
     * Company Method - Add Coupon
     */
    public void AddCoupon() {
        System.out.println("*** Method: Add Coupon ***");
        Company company = restTemplate.getForObject
                ("http://localhost:8080/Company/GetCompanyDetails",Company.class);
        int companyId = company.getId();
        // Create new coupon
        Category category = Category.GetRandomCategory();
        String title = "Rest_CompAddCoupTitle"+GetrandInt(100);
        String description = "Rest_CompAddCoupDescription";
        LocalDate startDate = DateFactory.getLocalDate(false);
        LocalDate endDate = DateFactory.getLocalDate(true);
        double price = FactoryUtils.round(Math.random()*(maxPrice),2);
        int amount = GetrandInt(50);
        String image = "Image"+GetrandInt(10);

        Coupon coupon = Coupon.builder()
                .id(251)
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
        System.out.println(coupon);
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/Company/AddCoupon",coupon,String.class);
        System.out.print("Added Coupon? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();

    }

    /**
     * Company Method - Update Coupon
     */
    public void UpdateCoupon() {
        System.out.println("*** Method: Update Coupon ***");
        // Get all company coupons from DB
        List<Coupon> couponList = GetListOfCompanyCoupons();

        // Select random ID for updating company
        int updateCouponId = GetRandIdFromList(couponList);
        Coupon updatedCoup = restTemplate.getForObject
                ("http://localhost:8080/Company/GetOneCoupon/"+updateCouponId,Coupon.class);

        // Update fields
        updatedCoup.setTitle("Rest_CompUpdated"+GetrandInt(50));
        updatedCoup.setDescription("Rest_CompUpdated"+GetrandInt(100));
        updatedCoup.setAmount(GetrandInt(50));
        updatedCoup.setCategory(Category.GetRandomCategory());
        updatedCoup.setImage("Rest_CompUpdated");
        updatedCoup.setStart_date(DateFactory.getLocalDate(false));
        updatedCoup.setEnd_date(DateFactory.getLocalDate(true));

        // Update company in DB
        System.out.println("Coupon to update: ");
        System.out.println(updatedCoup);
        restTemplate.put
                ("http://localhost:8080/Company/UpdateCoupon/"+updatedCoup.getId(),updatedCoup);
        System.out.println("Updated Coupon? true");
        System.out.println();
    }

    /**
     * Company Method - Delete Coupon
     */
    public void DeleteCoupon() {
        System.out.println("*** Method: Delete Coupon ***");
        // Get all company coupons from DB
        List<Coupon> couponList = GetListOfCompanyCoupons();
        // Select random ID for updating company
        int deleteCouponId = GetRandIdFromList(couponList);
        Coupon coupToDelete = restTemplate.getForObject
                ("http://localhost:8080/Company/GetOneCoupon/"+deleteCouponId, Coupon.class);
        System.out.println("Coupon to delete: \n"+coupToDelete);
        // Delete company
        restTemplate.delete("http://localhost:8080/Company/DeleteCoupon/"+deleteCouponId);
        System.out.println("Deleted Coupon? true");
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons
     */
    public void GetCompanyCoupons() {
        System.out.println("*** Method: Get Company Coupons ***");
        // Get all company coupons
        List<Coupon> coupons = GetListOfCompanyCoupons();
        // Display coupons
        System.out.println("Company's Coupons: ");
        coupons.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by category
     */
    public void GetCompanyCouponsByCategory() {
        System.out.println("*** Method: Get Company Coupons by Category ***");
        Category category = Category.GetRandomCategory();
        // Get company coupons by category
        Coupon[] coupons = restTemplate.getForObject
                ("http://localhost:8080/Company/GetCompanyCouponsByCategory/"+category, Coupon[].class);
        List<Coupon> couponsByCategory = Arrays.stream(coupons).toList();
        // Display coupons
        System.out.println("Company's Coupons by Category '"+category+"': ");
        couponsByCategory.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Company Method - Get Company Coupons by max Price
     */
    public void GetCompanyCouponsByMaxPrice() {
        System.out.println("*** Method: Get Company Coupons by Max Price ***");
        double price = Math.random()*maxPrice;
        // Get company coupons by max price
        Coupon[] coupons = restTemplate.getForObject
                ("http://localhost:8080/Company/GetCompanyCouponsByMaxPrice/"+price, Coupon[].class);
        List<Coupon> couponsByPrice = Arrays.stream(coupons).toList();
        // Display coupons
        System.out.println("Company's Coupons by Max Price "+ FactoryUtils.beautifyPrice(price) +": ");
        couponsByPrice.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Creates a list of all company coupons (convert array to list)
     * @return List of all coupons listed for the company in DB
     */
    private List<Coupon> GetListOfCompanyCoupons() {
        Coupon[] coupons = restTemplate.getForObject
                ("http://localhost:8080/Company/GetCompanyCoupons", Coupon[].class);
        return Arrays.stream(coupons).toList();
    }
}
