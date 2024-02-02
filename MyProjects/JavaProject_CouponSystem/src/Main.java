import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.DButils;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;

import static DataBase.DAO.DB_DAO.DB_DAO_MockData.FillInCustomerTable;
import static DataBase.DAO.DB_DAO.DB_DAO_MockData.FillInCustomerVsCouponsTable;

public class Main {
    public static void main(String[] args) {
        //InitDB.InitiateDB();
        try {
            FillInCustomerVsCouponsTable();
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
