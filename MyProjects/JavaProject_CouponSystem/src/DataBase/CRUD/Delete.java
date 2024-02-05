package DataBase.CRUD;

import DataBase.DBmanager;

public class Delete {

    // Company

    // Category

    // Coupons

    public static final String deleteCoupon =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`coupons` WHERE (`id` = ?);";

    public static final String deleteCouponPurchase =
            "DELETE FROM `"+ DBmanager.SQL_DB+"`.`customers_vs_coupons` WHERE (`customerID` = ?) and (`couponID` = ?);";

    // Customer
}
