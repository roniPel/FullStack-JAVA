package DataBase.CRUD;

import DataBase.DBmanager;

/**
 * Class for Read commands in SQL
 */
public class Read {
    // General
    public static final String isSchemaExists =
            "SELECT COUNT(SCHEMA_NAME)" +
                    "  FROM INFORMATION_SCHEMA.SCHEMATA" +
                    " WHERE SCHEMA_NAME = '"+ DBmanager.SQL_DB+"'";

    // Company
    public static final String isCompanyExists =
            "SELECT COUNT(*) FROM "+ DBmanager.SQL_DB+".companies WHERE email = ? AND password = ? ;";

    public static final String getCompanyIdByEmail =
            "SELECT id FROM "+ DBmanager.SQL_DB+".companies WHERE email = ? ;";
    public static final String isCompanyEmailExists =
            "SELECT COUNT(*) FROM "+ DBmanager.SQL_DB+".companies WHERE email = ? ;";
    public static final String isCompanyIdExists =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".companies WHERE id = ?;";
    public static final String getAllCompanies =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies;";
    public static final String getOneCompany =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies WHERE id = ? ;";
    public static final String countNumberOfCompanies =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".companies;";

    // Category
    public static final String getCategoryID =
            "SELECT id FROM "+DBmanager.SQL_DB+".categories WHERE name = ? ;";
    public static final String getAllCategories =
            "SELECT * FROM "+DBmanager.SQL_DB+".categories;";
    public static final String countNumberOfCategories =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".categories;";


    // Coupons
    public static final String getExpiredCoupons =
            "SELECT * FROM "+DBmanager.SQL_DB+".coupons WHERE endDate <= DATE(NOW());";
    public static final String countNumberOfCoupons =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".coupons;";
    public static final String getAllCoupons =
            "SELECT * FROM "+DBmanager.SQL_DB+".coupons;";
    public static final String getCouponsForCompany =
            "SELECT * FROM "+DBmanager.SQL_DB+".coupons WHERE companyID = ?;";
    public static final String getCouponsForCustomer =
            "SELECT * FROM "+DBmanager.SQL_DB+".customers_vs_coupons WHERE customerID = ?;";

    public static final String getCouponIdByTitle =
            "SELECT id FROM "+ DBmanager.SQL_DB+".coupons WHERE title = ? ;";
    public static final String getCouponsById =
            "SELECT * FROM "+DBmanager.SQL_DB+".coupons WHERE id IN (?);";


    // Customer
    public static final String getCustomerIdByEmail =
            "SELECT id FROM "+ DBmanager.SQL_DB+".customers WHERE email = ? ;";
    public static final String isCustomerExists =
            "SELECT COUNT(*) FROM "+ DBmanager.SQL_DB+".customers WHERE email = ? AND password = ? ;";

    public static final String isCustomerIdExists =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".customers WHERE id = ?;";
    public static final String countNumberOfCustomers =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".customers;";

    public static final String getAllCustomers =
            "SELECT * FROM "+DBmanager.SQL_DB+".customers;";

    public static final String getOneCustomer =
            "SELECT * FROM "+DBmanager.SQL_DB+".customers WHERE id = ?;";

    public static final String countCustomersVsCoupons =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".customers_vs_coupons;";

    public static final String getCustomersVsCoupons =
            "SELECT * FROM "+DBmanager.SQL_DB+".customers_vs_coupons;";
    public static final String getCouponIDCustomersVsCoupons =
            "SELECT couponID FROM "+DBmanager.SQL_DB+".customers_vs_coupons;";

}
