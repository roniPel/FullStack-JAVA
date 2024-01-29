package DataBase.CRUD;

import DataBase.DBmanager;

public class Update {
    public static final String updateCompany =
            "UPDATE `"+ DBmanager.SQL_DB+"`.`companies` SET `name` = ?, " +
                    "`email` = ?, `password` = ? WHERE (`id` = ?);";
}
