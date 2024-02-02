import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.DButils;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;

import static DataBase.DAO.DB_DAO.DB_DAO_MockData.FillInCustomerTable;

public class Main {
    public static void main(String[] args) {
        //InitDB.InitiateDB();
        try {
            FillInCustomerTable(50);
        } catch (CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
