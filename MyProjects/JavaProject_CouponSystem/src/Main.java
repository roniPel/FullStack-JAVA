import Beans.Company;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Company company = DataBase.DAO.DB_DAO.CompaniesDB_DAO.GetOneCompany(10);
            System.out.println(company);
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
