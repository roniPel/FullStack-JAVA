package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    protected CouponRepository couponRepo;
    @Autowired
    protected CompanyRepository companyRepo;
    @Autowired
    protected CustomerRepository customerRepo;

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
        return false;
    }

    @Override
    public void AddCoupon() throws CompanyException {

    }

    @Override
    public void UpdateCoupon() throws CompanyException {

    }

    @Override
    public void DeleteCoupon() throws CompanyException {

    }

    @Override
    public void GetCompanyCoupons() throws CompanyException {

    }

    @Override
    public void GetCompanyCouponsByCategory() throws CompanyException {

    }

    @Override
    public void GetCompanyCouponsByMaxPrice() throws CompanyException {

    }

    @Override
    public void GetCompanyDetails() throws CompanyException {

    }

    //Todo - write all methods


}
