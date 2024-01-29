import Beans.Company;
import DAO.CompaniesDB_DAO;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;

public class Main {
    public static void main(String[] args) {
        try {
            Company company = new Company(5,"NASA","nasa.fake@gmail.com","nasaPass",null);
            CompaniesDB_DAO.AddCompany(company);
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
