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

    //Todo - write all methods

}
