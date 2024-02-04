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
            "UPDATE `"+ DBmanager.SQL_DB+"`.`coupons` SET `amount` = `amount` = `amount`-1 WHERE `id` IN (?);";

    // Customer

}
