package DataBase.CRUD;

import DataBase.DBmanager;

public class Create {

    // Company
    public static final String insertCompany =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";


    // Category
    public static final String insertCategory =
            "INSERT INTO `"+ DBmanager.SQL_DB+"`.`categories` (`name`) VALUES (?);";


    // Coupons
    public static final String insertCoupon =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`coupons` (`companyID`, `categoryID`, `title`, `description`, " +
                    "`startDate`, `endDate`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";


    // Customer
    public static final String insertCustomer =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`customers` (`first_name`, `last_name`, `email`, `password`) " +
                    "VALUES (?, ?, ?, ?);";
}