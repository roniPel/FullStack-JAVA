package DataBase.CRUD;

import DataBase.DBmanager;

public class Update {

    // Company
    public static final String updateCompany =
            "UPDATE `"+ DBmanager.SQL_DB+"`.`companies` SET `name` = ?, " +
                    "`email` = ?, `password` = ? WHERE (`id` = ?);";

    // Category


    // Coupons
    public static final String updateCouponsAmount =
            "UPDATE `"+ DBmanager.SQL_DB+"`.`coupons` SET `amount` = `amount`-1 WHERE `id` IN (?);";

    public static final String updateCoupon =
            "UPDATE `"+ DBmanager.SQL_DB+"`.`coupons` SET `companyID` = ?, " +
                    "`categoryID` = ?, `title` = ?, `description` = ?, `startDate` = ?, `endDate` = ?, " +
                    "`amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
    // Customer

}
