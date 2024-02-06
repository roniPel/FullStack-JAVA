import Beans.Category;
import Beans.Coupon;
import Beans.Customer;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.DButils;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;

import java.time.LocalDate;
import java.util.ArrayList;

import static DataBase.DAO.DB_DAO.DB_DAO_MockData.*;
import static Utils.DateFactory.getLocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            /*// Create DB + fill in data
            InitDB.InitiateDB();
            FillInCategoryTable();
            FillInCompanyTable(5);
            CreateCouponsForAllCompanies(50,40,156.99);
            FillInCustomerTable(30);
            FillInCustomerVsCouponsTable();*/

            // TESTING //

        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
