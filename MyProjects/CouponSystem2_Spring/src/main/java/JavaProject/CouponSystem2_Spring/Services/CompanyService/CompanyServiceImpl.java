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
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(companyRepo.existsById(id)){
            throw new CompanyException(CompanyErrors.COUPON_EXISTS_FOR_COMPANY);
        }
        if(coupon.getB_company_id() != this.companyId){
            throw new CompanyException(CompanyErrors.COUPON_COMPANY_ID_INCORRECT);
        }
        couponRepo.save(coupon);
        return true;
    }

    @Override
    public boolean UpdateCoupon(Coupon coupon) throws CompanyException {
        return false;
    }

    @Override
    public boolean DeleteCoupon(int couponId) throws CompanyException {
        return false;
    }

    @Override
    public List<Coupon> GetCompanyCoupons() throws CompanyException {
        return null;
    }

    @Override
    public List<Coupon> GetCompanyCouponsByCategory(Category category) throws CompanyException {
        return null;
    }

    @Override
    public List<Coupon> GetCompanyCouponsByMaxPrice(Double maxPrice) throws CompanyException {
        return null;
    }

    @Override
    public Company GetCompanyDetails() throws CompanyException {
        return null;
    }


}
