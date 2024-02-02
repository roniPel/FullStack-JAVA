package DataBase.DAO.DB_DAO;

import Beans.Category;
import Beans.Company;
import DataBase.CRUD.Read;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;
import Utils.DateFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static DataBase.DButils.runQueryWithMap;
import static DataBase.DButils.sqlInsertMultipleValues;
import static ErrorHandling.Errors.SQL_ERROR;

public class DB_DAO_MockData {

    /**
     * Creates links between coupons and customers for all the customers listed in the DB.  Steps:
     * 1 - check if DB contains customers
     * 2 - check if DB contains coupons
     * 3 - Link coupons and customers + update the correct amount
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean FillInCustomerVsCouponsTable() throws CouponSystemException {
        // Part 1 - check if DB contains customers
        int numberOfCustomers = isDBcontainsCustomers();
        if(numberOfCustomers > 0) {
            ArrayList<Company> customers = CompaniesDB_DAO.GetAllCompanies();

            // Part 2 - check if DB contains categories
            if (isDBcontainsCategories() > 0) {
                // Part 2 - check if DB contains coupons

                // Part 3 - Create item in Customers_vs_coupons table
            }
        }
        return false;
    }

    /**
     * Fills in customer table with the number of customers the user wants to enter
     * @param numberOfCustomers number of customers to insert into DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean FillInCustomerTable(int numberOfCustomers) throws CouponSystemException {
        // Prepare params Map with Company values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 1; i <= numberOfCustomers; i++) {
            params.put(counter++,"FirstName"+i);
            params.put(counter++,"LastName"+i);
            params.put(counter++,"Customer"+i+"@hotmail.com");
            params.put(counter++,"PassCust"+i);
        }
        // Prepare multiple insert SQL statement
        String sql = sqlInsertMultipleValues(numberOfCustomers, "Customer");
        // Run query in DB
        if(runQueryWithMap(sql, params));
        else {
            return false;
        }
        return true;
    }


    /**
     * Fills in category table with all categories in 'Category' Enum
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean FillInCategoryTable() throws CouponSystemException {
        String category;

        // Prepare params Map with Category values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < Category.values().length; i++) {
            category = String.valueOf(Category.values()[i]);
            params.put(counter,category);
            counter++;
        }
        // Prepare multiple insert SQL statement
        String sql = sqlInsertMultipleValues(Category.values().length, "Category");

        // Run query in DB
        if(runQueryWithMap(sql, params))
            return true;
        else {
            return false;
        }
    }


    /**
     * Fills in company table with the number of companies the user wants to enter
     * @param numberOfCompanies number of companies to insert into DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean FillInCompanyTable(int numberOfCompanies) throws CouponSystemException {
        // Prepare params Map with Company values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 1; i <= numberOfCompanies; i++) {
            params.put(counter++,"Company"+i);
            params.put(counter++,"Company"+i+"@hotmail.com");
            params.put(counter++,"PassComp"+i);
        }
        // Prepare multiple insert SQL statement
        String sql = sqlInsertMultipleValues(numberOfCompanies, "Company");
        // Run query in DB
        if(runQueryWithMap(sql, params));
        else {
            return false;
        }
        return true;
    }


    /**
     * Creates coupons for all the companies listed in the DB.  Steps:
     * 1 - check if DB contains companies
     * 2 - check if DB contains categories
     * 3 - create coupons in DB for all companies
     * @param numberOfCouponsePerCompany number of coupons to add to each company
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean CreateCouponsForAllCompanies(int numberOfCouponsePerCompany, int amountCouponsPerType,
                                                       double maxPrice) throws CouponSystemException {

        // Part 1 - check if DB contains companies
        int numOfCompanies = IsDBcontainsCompanies();
        if(numOfCompanies > 0) {
            ArrayList<Company> companies = CompaniesDB_DAO.GetAllCompanies();

            // Part 2 - check if DB contains categories
            if(isDBcontainsCategories() > 0) {

                // Part 3 - create coupons in DB

                // Prepare multiple insert SQL statement
                String sql = sqlInsertMultipleValues(numberOfCouponsePerCompany, "Coupon");

                // Add coupons for each company
                for(Company company: companies) {
                    // Prepare params Map with random Coupon values
                    Map<Integer,Object> params = createMapRandomCoupons(company.getId(),
                            numberOfCouponsePerCompany,amountCouponsPerType,maxPrice);

                    // Insert coupons into DB:
                    if(runQueryWithMap(sql,params)) ;
                    else {  // If coupons were not added to DB - query failed
                        return false;
                    }
                    params.clear();
                }
                return true;
            }
            else {  // If DB doesn't contain categories
                return false;
            }
        }
        else {  // If DB doesn't contain companies
            return false;
        }
    }

    /**
     * Creates a map of random coupons linked to companyID provided in param
     * @param companyID used to create the coupon
     * @param numCouponsPerComp number of coupons for each company
     * @param amountCouponsPerType amount of coupons for each type of coupon
     * @param maxPrice maximum price for each coupon
     * @return Map<Integer,Object> with params if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static Map<Integer, Object> createMapRandomCoupons(int companyID, int numCouponsPerComp,
                                                         int amountCouponsPerType, double maxPrice) throws CouponSystemException {
        // Get categories currently in DB
        Map<Integer, String> categories = GetAllCategories();

        // Prepare params Map with Coupon values
        Map<Integer, Object> params = new HashMap<>();
        int count = 1;
        for (int i = 0; i < numCouponsPerComp; i++) {
            // Generate random parameters
            int categoryID = GetRandomCategoryIdFromMap(categories);
            String title = "Title"+i+" Company"+companyID;
            String description = "Description"+i;
            Date startDate = Date.valueOf(DateFactory.getLocalDate(false));
            Date endDate = Date.valueOf(DateFactory.getLocalDate(true));
            double price = Math.random()*(maxPrice+1);
            String image = "Image"+i;

            // Add params to map
            params.put(count++,companyID);
            params.put(count++,categoryID);
            params.put(count++,title);
            params.put(count++,description);
            params.put(count++,startDate);
            params.put(count++,endDate);
            params.put(count++, amountCouponsPerType);
            params.put(count++,price);
            params.put(count++,image);
        }
        return params;
    }


    /**
     * Provides a random categoryID based on details in param
     * @param categories Map containing CategoryIDs and Category names as listed in DB
     * @return int with a random category ID
     */
    private static int GetRandomCategoryIdFromMap(Map<Integer, String> categories) {
        return (int) (Math.random()*(categories.size()) )+1;
    }


    /**
     * Gets a map of all the categories listed in the DB
     * @return a map of categoryID (Integer) and name (String) if succeeded, 'null' if failed or if no categories exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static Map<Integer, String> GetAllCategories() throws CouponSystemException {
        // Part 1 - Get categories - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(DataBase.CRUD.Read.getAllCategories,params);

        // Part 2 - add results to Category Map
        Map<Integer,String> categories = new HashMap<>();
        try {
            while (results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                // Insert data into map
                categories.put(id,name);
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return categories;
    }


    /**
     * Checks whether the DB contains Companies
     * @return int with number of companies in DB if succeeded, -1 if failed or if no companies exist in DB
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static int IsDBcontainsCompanies() throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(Read.getNumberOfCompanies,params);
        try {
            int numberOfCompanies = -1;
            while (results.next()) {
                numberOfCompanies = results.getInt(1);
            }
            return numberOfCompanies;
        }
        catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }


    /**
     * Checks whether the DB contains categories
     * @return number of categories in DB if succeeded, -1 if failed or if no categories exist in DB
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static int isDBcontainsCategories() throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(Read.getNumberOfCompanies,params);
        try {
            int numberOfCompanies = -1;
            while (results.next()) {
                numberOfCompanies = results.getInt(1);
            }
            return numberOfCompanies;
        }
        catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }


    /**
     * Checks whether the DB contains customers
     * @return number of customers in DB if succeeded, -1 if failed or if no customers exist in DB
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static int isDBcontainsCustomers() throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(Read.getNumberOfCustomers,params);
        try {
            int numberOfCustomers = -1;
            while (results.next()) {
                numberOfCustomers = results.getInt(1);
            }
            return numberOfCustomers;
        }
        catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }


    /**
     * Checks whether the DB contains coupons
     * @return number of coupons in DB if succeeded, -1 if failed or if no coupons exist in DB
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static int isDBcontainsCoupons() throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(Read.getNumberOfCustomers,params);
        try {
            int numberOfCoupons = -1;
            while (results.next()) {
                numberOfCoupons = results.getInt(1);
            }
            return numberOfCoupons;
        }
        catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }
}
