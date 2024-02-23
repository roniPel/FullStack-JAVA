package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    protected CouponRepository couponRepo;
    @Autowired
    protected CompanyRepository companyRepo;
    @Autowired
    protected CustomerRepository customerRepo;

    //Todo - write all methods

}
