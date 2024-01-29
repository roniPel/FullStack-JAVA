package DataBase;

import ErrorHandling.CouponSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static ErrorHandling.Errors.SQL_ERROR;

public class InitDB {
    // Create DB (schema)
    //Todo - delete? public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS `couponsystemdb`" ;
    //Todo - delete? public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS '"+DBmanager.SQL_DB+"'" ;
    public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS "+DBmanager.SQL_DB;

    // Create tables
    public static final String CREATE_TABLE_COMPANIES =
            "CREATE TABLE IF NOT EXISTS `"+DBmanager.SQL_DB+"`.`companies` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NULL," +
                    "  `email` VARCHAR(45) NULL," +
                    "  `password` VARCHAR(10) NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);";

    public static final String CREATE_TABLE_CUSTOMERS =
            "CREATE TABLE IF NOT EXISTS `"+DBmanager.SQL_DB+"`.`customers` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `first_name` VARCHAR(45) NULL," +
                    "  `last_name` VARCHAR(45) NULL," +
                    "  `email` VARCHAR(45) NULL," +
                    "  `password` VARCHAR(10) NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);";

    public static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS `"+DBmanager.SQL_DB+"`.`categories` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);";

    public static final String CREATE_TABLE_COUPONS =
            "CREATE TABLE IF NOT EXISTS `"+DBmanager.SQL_DB+"`.`coupons` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `companyID` INT NULL," +
                    "  `categoryID` INT NULL," +
                    "  `title` VARCHAR(45) NULL," +
                    "  `description` VARCHAR(45) NULL," +
                    "  `startDate` DATE NULL," +
                    "  `endDate` DATE NULL," +
                    "  `amount` INT NULL," +
                    "  `price` DOUBLE NULL," +
                    "  `image` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE);";

    public static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS =
            "CREATE TABLE IF NOT EXISTS `"+DBmanager.SQL_DB+"`.`customers_vs_coupons` (" +
                    "  `customerID` INT NOT NULL," +
                    "  `couponID` INT NOT NULL," +
                    "  PRIMARY KEY (`customerID`, `couponID`));";

    // Link Tables
    public static final String LINK_BETWEEN_TABLES_COUPONS_COMPANIES_CATEGORIES =
            "ALTER TABLE `"+DBmanager.SQL_DB+"`.`coupons` " +
                    "ADD INDEX `companyID_idx` (`companyID` ASC) VISIBLE," +
                    "ADD INDEX `categoryFK_idx` (`categoryID` ASC) VISIBLE;" +
                    "ALTER TABLE `"+DBmanager.SQL_DB+"`.`coupons` " +
                    "ADD CONSTRAINT `companyFK`" +
                    "  FOREIGN KEY (`companyID`)" +
                    "  REFERENCES `"+DBmanager.SQL_DB+"`.`companies` (`id`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE CASCADE," +
                    "ADD CONSTRAINT `categoryFK`" +
                    "  FOREIGN KEY (`categoryID`)" +
                    "  REFERENCES `"+DBmanager.SQL_DB+"`.`categories` (`id`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE CASCADE;";

    public static final String LINK_BETWEEN_TABLES_CUSTOMERS_VS_COUPONS =
            "ALTER TABLE `"+DBmanager.SQL_DB+"`.`customers_vs_coupons` " +
                    "ADD INDEX `couponsFK_idx` (`couponID` ASC) VISIBLE;" +
                    "ALTER TABLE `"+DBmanager.SQL_DB+"`.`customers_vs_coupons` " +
                    "ADD CONSTRAINT `customersFK`" +
                    "  FOREIGN KEY (`customerID`)" +
                    "  REFERENCES `"+DBmanager.SQL_DB+"`.`customers` (`id`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE CASCADE," +
                    "ADD CONSTRAINT `couponsFK`" +
                    "  FOREIGN KEY (`couponID`)" +
                    "  REFERENCES `"+DBmanager.SQL_DB+"`.`coupons` (`id`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE CASCADE;";

    public static void InitiateDB() throws CouponSystemException {
        // Create schema, all tables, links, and foreign keys

        // Add SQL commands to set
        List<String> sqlCommands = new ArrayList<>();
        sqlCommands.add(CREATE_SCHEMA);
        sqlCommands.add(CREATE_TABLE_COMPANIES);
        sqlCommands.add(CREATE_TABLE_CUSTOMERS);
        sqlCommands.add(CREATE_TABLE_CATEGORIES);
        sqlCommands.add(CREATE_TABLE_COUPONS);
        sqlCommands.add(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
        sqlCommands.add(LINK_BETWEEN_TABLES_COUPONS_COMPANIES_CATEGORIES);
        sqlCommands.add(LINK_BETWEEN_TABLES_CUSTOMERS_VS_COUPONS);

        // Iterate over SQL commands and inform if failed or succeeded
        for(String command: sqlCommands) {
            if(runQuery(command));
            else {
                System.out.println("DB initiation failed");
                return;
            }
        }
        System.out.println("Database was initiated successfully");
    }

    private static boolean runQuery(String sql) throws CouponSystemException {
        // private function - only used for initiating DB

        //use connection from connection sql to send queries to our DB
        Connection connection = null;

        try {
            //get a connection from connection pool
            connection = ConnectionPool.getInstance().getConnection();

            //prepare our sql (String) and convert it to a language that mysql will understand
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //run statement
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(SQL_ERROR.getMessage()+e);
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}