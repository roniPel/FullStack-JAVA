package DataBase.CRUD;

import DataBase.DBmanager;

public class Read {

    // Company
    public static final String companyExists =
            "SELECT COUNT(*) FROM "+ DBmanager.SQL_DB+".companies WHERE email = ? AND password = ? ;";
    public static final String getCompanyID =
            "SELECT id FROM "+DBmanager.SQL_DB+".companies WHERE name = ? ;";
    public static final String getAllCompanies =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies;";
    public static final String getOneCompany =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies WHERE id = ? ;";
    public static final String getNumberOfCompanies =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".companies;";

    // Category
    public static final String getCategoryID =
            "SELECT id FROM "+DBmanager.SQL_DB+".categories WHERE name = ? ;";
    public static final String getAllCategories =
            "SELECT * FROM "+DBmanager.SQL_DB+".categories;";

    public static final String getNumberOfCategories =
            "SELECT COUNT(*) FROM "+DBmanager.SQL_DB+".categories;";


    // Coupons


    // Customer
}
