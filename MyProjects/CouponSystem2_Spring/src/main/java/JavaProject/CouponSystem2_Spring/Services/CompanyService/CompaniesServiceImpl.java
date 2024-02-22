package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompaniesServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepo;
    //Todo - write all methods
    @Override
    public boolean IsCompanyExists(String email, String password) throws CompanyException {
        return false;
    }

    @Override
    public int GetCompanyIDByEmail(String email) throws CompanyException {
        return 0;
    }

    @Override
    public boolean IsCompanyIdExists(int companyID) throws CompanyException {
        return false;
    }

    @Override
    public boolean AddCompany(Company company) throws CompanyException {
        return false;
    }

    @Override
    public boolean DeleteCompany(int companyID) throws CompanyException {
        return false;
    }

    @Override
    public boolean UpdateCompany(Company company) throws CompanyException {
        return false;
    }

    @Override
    public ArrayList<Company> GetAllCompanies() throws CompanyException {
        return null;
    }

    @Override
    public Company GetOneCompany(int companyID) throws CompanyException {
        return null;
    }
}
