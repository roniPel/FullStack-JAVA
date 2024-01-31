package DataBase.CRUD;

import DataBase.DBmanager;

public class Update {

    // Company
    public static final String updateCompany =
            "UPDATE `"+ DBmanager.SQL_DB+"`.`companies` SET `name` = ?, " +
                    "`email` = ?, `password` = ? WHERE (`id` = ?);";

    // Category


    // Coupons


    // Customer

}
