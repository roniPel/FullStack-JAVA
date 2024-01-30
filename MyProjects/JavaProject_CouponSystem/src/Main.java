import Beans.Company;
import Beans.Coupon;
import DataBase.DB_DAO.CompaniesDB_DAO;
import ErrorHandling.CouponSystemException;

public class Main {
    public static void main(String[] args) {
        try {
            DataBase.DB_DAO.CompaniesDB_DAO.UpdateCompany(new Company(4,"Facebook","face.book@hotmail.com","facePass",null));
//            Company company = new Company(5,"NASA","nasa.fake@gmail.com","nasaPass",null);
//            CompaniesDB_DAO.AddCompany(company);
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
