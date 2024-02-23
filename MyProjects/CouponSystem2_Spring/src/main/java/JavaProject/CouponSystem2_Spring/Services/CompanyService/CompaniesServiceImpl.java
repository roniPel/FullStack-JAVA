package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Service
public class CompaniesServiceImpl implements CompanyService {

    @Autowired
    protected CouponRepository couponRepo;
    @Autowired
    protected CompanyRepository companyRepo;
    @Autowired
    protected CustomerRepository customerRepo;

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
