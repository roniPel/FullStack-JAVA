package DataBase;



public class SQLcommands {
    // CRUD commands to send to SQL DB:

    // Create
    public static final String insertCategory =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`categories` (`name`) VALUES (?);";
    public static final String insertCompany =
            //"INSERT INTO `"+DBmanager.SQL_DB+"`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`companies` (`id`,`name`, `email`, `password`) VALUES (?, ?, ?, ?);";
    public static final String insertCoupon =
            "INSERT INTO `"+DBmanager.SQL_DB+"`.`coupons` (`companyID`, `categoryID`, `title`, `description`, " +
                    "`startDate`, `endDate`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String insertCustomer =
            "";


    // Read
    public static final String companyExists =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".companies WHERE email = ? AND password = ? ;";
    public static final String getCompanyID =
            "SELECT id FROM "+DBmanager.SQL_DB+".companies WHERE name = ? ;";
    public static final String getCategoryID =
            "SELECT id FROM "+DBmanager.SQL_DB+".categories WHERE name = ? ;";
    public static final String getAllCompanies =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies;";

    public static final String getOneCompany =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies WHERE id = ? ;";


    // Update
    public static final String updateCompany =
            "UPDATE `"+DBmanager.SQL_DB+"`.`companies` SET `name` = ?, " +
                    "`email` = ?, `password` = ? WHERE (`id` = ?);";


    // Delete


}
