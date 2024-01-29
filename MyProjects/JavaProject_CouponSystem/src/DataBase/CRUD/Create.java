package DataBase.CRUD;

import DataBase.DBmanager;

public class Create {
    public static final String insertCategory =
            "INSERT INTO `"+ DBmanager.SQL_DB+"`.`categories` (`name`) VALUES (?);";
    public static final String insertCompany =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";
    public static final String insertCoupon =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`coupons` (`companyID`, `categoryID`, `title`, `description`, " +
                    "`startDate`, `endDate`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String insertCustomer =
            "";
}
