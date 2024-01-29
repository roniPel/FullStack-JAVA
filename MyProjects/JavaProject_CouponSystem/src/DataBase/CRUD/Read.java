package DataBase.CRUD;

import DataBase.DBmanager;

public class Read {
    public static final String companyExists =
            "SELECT COUNT(*) FROM "+ DBmanager.SQL_DB+".companies WHERE email = ? AND password = ? ;";
    public static final String getCompanyID =
            "SELECT id FROM "+DBmanager.SQL_DB+".companies WHERE name = ? ;";
    public static final String getCategoryID =
            "SELECT id FROM "+DBmanager.SQL_DB+".categories WHERE name = ? ;";
    public static final String getAllCompanies =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies;";

    public static final String getOneCompany =
            "SELECT * FROM "+DBmanager.SQL_DB+".companies WHERE id = ? ;";
}
