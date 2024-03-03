package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CouponRepository couponRepo;
    @Autowired
    CompanyRepository companyRepo;

    private int companyId;  // Company ID belonging to the company that logged in

    /**
     * Constructor
     * @param companyId company ID belonging to company logged on
     */
    public CompanyServiceImpl(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException {
        Company company = companyRepo.findByEmailAndPassword(email,password);
        if(company == null) {
            throw new CompanyException(CompanyErrors.INCORRECT_LOGIN_DETAILS);
        }
        else {
            this.companyId = company.getId();
            return true;
        }
    }

    @Override
    public boolean AddCoupon(Coupon coupon) throws CompanyException {
        int id = coupon.getId();
        if(couponRepo.existsById(id)){  //Check if coupon Id exists in DB
            throw new CompanyException(CompanyErrors.DUPLICATE_ENTRY);
        }
        if(couponRepo.findByTitleAndCompanyId(coupon.getTitle(),id) != null) {    // Check if coupon title exists for this company
            throw new CompanyException(CompanyErrors.COUPON_EXISTS_FOR_COMPANY);
        }
        if(coupon.getCompanyId() != this.companyId){ //Verify coupon contains correct company Id
            throw new CompanyException(CompanyErrors.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
        couponRepo.save(coupon);
        return true;
    }

    @Override
    public boolean UpdateCoupon(Coupon coupon) throws CompanyException {
        int id = coupon.getId();
        // Verify coupon exists in DB
        Coupon currentCoupon = couponRepo.findById(id).orElseThrow(
                () -> new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST)
        );
        // Verify company Id in new coupon matches company id listed in existing coupon
        if(!Objects.equals(coupon.getCompanyId(), currentCoupon.getCompanyId())) {
            throw new CompanyException(CompanyErrors.COUPON_COMPANY_ID_INCORRECT);
        }
        couponRepo.saveAndFlush(coupon);
        return true;
    }

    @Override
    public boolean DeleteCoupon(int couponId) throws CompanyException {
        if(!couponRepo.existsById(couponId)){
            throw new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST);
        }
        couponRepo.deleteById(couponId);
        return true;
    }

    @Override
    public List<Coupon> GetCompanyCoupons() {
        return couponRepo.findByCompanyId(this.companyId);
    }

    @Override
    public List<Coupon> GetCompanyCouponsByCategory(Category category) {
        return couponRepo.findByCompanyIdAndCategory(this.companyId, category);
    }

    @Override
    public List<Coupon> GetCompanyCouponsByMaxPrice(Double maxPrice) {
        return couponRepo.findByCompanyIdAndPriceLessThanEqual(this.companyId, maxPrice);
    }

    @Override
    public Company GetCompanyDetails() throws CompanyException {
        return companyRepo.findById(this.companyId).orElseThrow(
                () -> new CompanyException(CompanyErrors.GENERAL_COMPANY_ERROR) );
    }

    /**
     * Add coupons from all categories to a new company
     * @return the new company id
     */
    public int AddCompanyWithFullCoupons() {
        // Add Company to DB
        Company company = Company.builder()
                .id(100)
                .name("CompanyFullCoupons")
                .email("CompCoupons@email.com")
                .password("Pass")
                .build();
        companyRepo.save(company);
        // Get company ID from DB
        int newCompanyId = companyRepo.findByName(company.getName()).getId();
        List<Coupon> fullCouponsList = CreateCompanyCouponsForAllCategories(newCompanyId);

        // Add coupon List to DB
        couponRepo.saveAllAndFlush(fullCouponsList);
        return newCompanyId;
    }

    private List<Coupon> CreateCompanyCouponsForAllCategories(int companyId) {
        List<Coupon> couponsList = new ArrayList<>();
        // Add coupons from all categories to coupon List (to company)
        int count = 200;
        for (Category category : Category.values()) {
            // Create coupon locally
            String title = "Title Company "+category;
            String description = "Description Company "+ category;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            int amount = 10;
            double price = FactoryUtils.round(Math.random()*200,2);
            String image = "Image Company "+category;
            Coupon addCoupon = Coupon.builder()
                    .id(count++)
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
            // Add coupon to coupon List
            couponsList.add(addCoupon);
        }
        return couponsList;
    }
}
