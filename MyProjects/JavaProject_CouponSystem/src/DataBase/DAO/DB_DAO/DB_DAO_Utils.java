package DataBase.DAO.DB_DAO;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DataBase.CRUD.Create;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;
import Utils.DateFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static DataBase.DButils.runQueryWithMap;
import static DataBase.DButils.sqlInsertMultipleValues;
import static ErrorHandling.Errors.SQL_ERROR;

public class DB_DAO_Utils {

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
        if(runQueryWithMap(sql, params));
        else {
            System.out.println("There was a problem creating the Category table");
            return false;
        }
        return true;
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
        for (int i = 1; i <= numberOfCompanies; i++) {
            params.put(1,"Company"+i);
            params.put(2,"Company"+i+"@hotmail.com");
            params.put(3,"PassComp"+i);
        }
        // Prepare multiple insert SQL statement
        String sql = sqlInsertMultipleValues(numberOfCompanies, "Company");
        // Run query in DB
        if(runQueryWithMap(sql, params));
        else {
            System.out.println("There was a problem filling the company table. ");
            return false;
        }
        System.out.println("Company table has been auto filled. ");
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
        if(numOfCompanies != 0) {
            ArrayList<Company> companies = CompaniesDB_DAO.GetAllCompanies();

            // Part 2 - check if DB contains categories
            if(isDBcontainsCategories()) {

                // Part 3 - create coupons in DB

                // Prepare multiple insert SQL statement
                String sql = sqlInsertMultipleValues(numberOfCouponsePerCompany, "Coupons");

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
        int count = 0;
        for (int i = 0; i < numCouponsPerComp; i++) {
            // Generate random parameters
            int categoryID = GetRandomCategoryIdFromMap(categories);
            String title = "Title"+i;
            String description = "Description"+i;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            int amount = (int) (Math.random()*amountCouponsPerType);
            double price = (int) (Math.random()*maxPrice);
            String image = "Image"+i;

            // Add params to map
            params.put(count++,companyID);
            params.put(count++,categoryID);
            params.put(count++,title);
            params.put(count++,description);
            params.put(count++,startDate);
            params.put(count++,endDate);
            params.put(count++,amount);
            params.put(count++,price);
            params.put(count++,image);
        }
        return params;
    }

    private static int GetRandomCategoryIdFromMap(Map<Integer, String> categories) {
        return (int) (Math.round(Math.random()*(categories.size())));
    }


    /**
     * Provides a random categoryID based on details in param
     * @param categories Map containing CategoryIDs and Category names as listed in DB
     * @return int with a random category ID
     */
    private static Category GetRandomCategoryFromMap(Map<Integer, String> categories) {
        int rand = (int) (Math.round(Math.random()*(categories.size())));
        String categoryName = categories.get(rand);
        return Category.valueOf(categoryName);
    }

    /**
     * Gets a map of all the categories listed in the DB
     * @return a map of categoryID (Integer) and name (String) if succeeded, 'null' if failed or if no categories exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static Map<Integer, String> GetAllCategories() throws CouponSystemException {
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
    private static int IsDBcontainsCompanies() {
        // Todo - Finish
        return -1;
    }

    /**
     * Checks whether the DB contains categories
     * @return true if succeeded, false if failed or if no categories exist in DB
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static boolean isDBcontainsCategories() {
        // Todo - Finish
        return false;
    }
}
