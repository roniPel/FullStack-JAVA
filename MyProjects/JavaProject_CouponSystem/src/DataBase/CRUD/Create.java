package DataBase.CRUD;

import DataBase.DBmanager;

/**
 * Class for Create commands in SQL
 */
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

    public static final String insertCustomerVsCoupon =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`customers_vs_coupons` (`customerID`, `couponID`) VALUES (?, ?);";
}
