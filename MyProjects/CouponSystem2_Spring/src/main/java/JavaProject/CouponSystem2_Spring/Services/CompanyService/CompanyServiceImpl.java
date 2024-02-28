package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletionException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    protected CouponRepository couponRepo;
    protected CompanyRepository companyRepo;

    private int companyId;  // Company ID belonging to the company that logged in

    /**
     * Constructor
     * @param companyId company ID belonging to company logged on
     */
    public CompanyServiceImpl(int companyId) {
        this.companyId = companyId;
    }

    //Todo - write all methods

    @Override
    public boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException {
        Company company = companyRepo.findByEmailAndPassword(email,password);
        if(company == null) {
            throw new CompanyException(CompanyErrors.INCORRECT_LOGIN_DETAILS);
        }
        else {
            this.companyId = company.getA_id();
            return true;
        }
    }

    @Override
    public boolean AddCoupon(Coupon coupon) throws CompanyException {
        int id = coupon.getA_id();
        if(couponRepo.existsById(id)){  //Check if coupon Id exists in DB
            throw new CompanyException(CompanyErrors.DUPLICATE_ENTRY);
        }
        if(couponRepo.findByTitleAndCompanyId(coupon.getD_title(),id) != null) {    // Check if coupon title exists for this company
            throw new CompanyException(CompanyErrors.COUPON_EXISTS_FOR_COMPANY);
        }
        if(coupon.getB_company_id() != this.companyId){ //Verify coupon contains correct company Id
            throw new CompanyException(CompanyErrors.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
        couponRepo.save(coupon);
        return true;
    }

    @Override
    public boolean UpdateCoupon(Coupon coupon) throws CompanyException {
        int id = coupon.getA_id();
        // Verify coupon exists in DB
        Coupon currentCoupon = couponRepo.findById(id).orElseThrow(
                () -> new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST)
        );
        // Verify company Id in new coupon matches company id listed in existing coupon
        if(!Objects.equals(coupon.getB_company_id(), currentCoupon.getB_company_id())) {
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
        return couponRepo.findByCompanyIdAndPriceLessThan(this.companyId, maxPrice);
    }

    @Override
    public Company GetCompanyDetails() throws CompanyException {
        return companyRepo.findById(this.companyId).orElseThrow(
                () -> new CompanyException(CompanyErrors.GENERAL_COMPANY_ERROR) );
    }
}
