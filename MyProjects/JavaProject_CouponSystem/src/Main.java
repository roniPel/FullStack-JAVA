import Beans.Company;
import DataBase.DB_DAO.CompaniesDB_DAO;
import ErrorHandling.CouponSystemException;
import Utils.FactoryUtils;

public class Main {
    public static void main(String[] args) {
        try {
            FactoryUtils.FillInCompanyTable(7);
//            Company company = new Company(5,"NASA","nasa.fake@gmail.com","nasaPass",null);
//            CompaniesDB_DAO.AddCompany(company);
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
