import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.DButils;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;

import static DataBase.DAO.DB_DAO.DB_DAO_MockData.*;

public class Main {
    public static void main(String[] args) {
        try {
            InitDB.InitiateDB();
            FillInCategoryTable();
            FillInCompanyTable(5);
            CreateCouponsForAllCompanies(50,40,156.99);
            FillInCustomerTable(30);
            FillInCustomerVsCouponsTable();
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
