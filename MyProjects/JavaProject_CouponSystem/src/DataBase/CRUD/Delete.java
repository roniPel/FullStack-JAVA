package DataBase.CRUD;

import DataBase.DBmanager;

public class Delete {

    // Company
    public static final String deleteCompany =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`companies` WHERE (`id` = ?);";

    // Category
    public static final String deleteCategory =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`categories` WHERE (`id` = ?);";

    // Coupons

    public static final String deleteCoupon =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`coupons` WHERE (`id` = ?);";

    public static final String deleteCouponPurchase =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`customers_vs_coupons` WHERE (`customerID` = ?) and (`couponID` = ?);";

    // Customer
    public static final String deleteCustomer =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`customers` WHERE (`id` = ?);";
}
