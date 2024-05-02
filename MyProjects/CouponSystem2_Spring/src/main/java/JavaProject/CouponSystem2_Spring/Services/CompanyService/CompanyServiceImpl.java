package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Company Service Implementation for Coupon System 2
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CouponRepository couponRepo;
    private final CompanyRepository companyRepo;
    private final CustomerRepository customerRepo;

    @Setter
    @Getter
    private int companyId;  // Company ID belonging to the company that logged in

    public void SetCompanyId(int companyId){
        this.companyId = companyId;
    }
    @Override
    public String Login(String email, String password) throws AdminException, CompanyException, CustomerException {
        return null;
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
            throw new CompanyException(CompanyErrors.NO_PERMISSIONS);
        }
        couponRepo.saveAndFlush(coupon);
        return true;
    }

    @Override
    public boolean DeleteCoupon(int couponId) throws CompanyException {
        Coupon coupon = couponRepo.findById(couponId).orElseThrow( ()->
                new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST));
        if (coupon.getCompanyId()!= this.companyId) {
            throw new CompanyException(CompanyErrors.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
        //Disconnect coupon from customer
        if(customerRepo.findByCoupons(coupon) != null) {
            List<Customer> customers = customerRepo.findByCoupons(coupon);
            customers.forEach(
              customer -> {
                  customer.getCoupons().remove(coupon);
              }
            );
            customerRepo.saveAllAndFlush(customers);
        }
        //Delete coupon
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

    @Override
    public Coupon GetOneCoupon(int id) throws CompanyException {
        Coupon coupon = couponRepo.findById(id).orElseThrow( ()->
                new CompanyException(CompanyErrors.COUPON_DOES_NOT_EXIST));
        if (coupon.getCompanyId()!= this.companyId) {
            throw new CompanyException(CompanyErrors.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
        return coupon;
    }

    @Override
    public Company GetOneCompany(int id) throws CompanyException {
        if(id != this.companyId){
            throw new CompanyException(CompanyErrors.NO_PERMISSIONS);
        }
        return companyRepo.findById(id).orElseThrow( ()->
                new CompanyException(CompanyErrors.GENERAL_COMPANY_ERROR));
    }

}
