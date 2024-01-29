package DataBase.DAO;

import Beans.Company;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;

public interface CompaniesDAO {
    static boolean IsCompanyExists(String email, String password) throws CouponSystemException{ return false;};
    static boolean AddCompany(Company company) throws CouponSystemException{return false;};
    static boolean UpdateCompany(Company company){return false;};
    static ArrayList<Company> GetAllCompanies(){return null;};
    static Company GetOneCompany(int companyID){return null;};
}
