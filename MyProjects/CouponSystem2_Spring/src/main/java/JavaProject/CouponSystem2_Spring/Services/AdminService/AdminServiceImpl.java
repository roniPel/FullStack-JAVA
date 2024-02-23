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

    @Override
    public boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException {
        return false;
    }

    @Override
    public void Method_GetOneCustomer() throws AdminException {

    }

    @Override
    public void Method_DeleteCustomer() throws AdminException {

    }

    @Override
    public void Method_UpdateCustomer() throws AdminException {

    }

    @Override
    public void Method_AddCustomer() throws AdminException {

    }

    @Override
    public void Method_GetAllCustomers() throws AdminException {

    }

    @Override
    public void Method_GetOneCompany() throws AdminException {

    }

    @Override
    public void Method_DeleteCompany() throws AdminException {

    }

    @Override
    public void Method_UpdateCompany() throws AdminException {

    }

    @Override
    public void Method_AddCompany() throws AdminException {

    }

    @Override
    public void Method_GetAllCompanies() throws AdminException {

    }

    @Override
    public String[] AddCompanyDetailsForLogin() throws AdminException {
        return new String[0];
    }

    @Override
    public String[] AddCustomerDetailsForLogin() throws AdminException {
        return new String[0];
    }

    //Todo - write all methods


}
