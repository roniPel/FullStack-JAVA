package DataBase.DAO.DB_DAO;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DataBase.CRUD.Read;
import DataBase.DButils;
import DataBase.SQLinsertMultipleValues;
import ErrorHandling.CouponSystemException;
import Utils.DateFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static DataBase.DButils.*;
import static ErrorHandling.Errors.SQL_ERROR;


public class DB_DAO_MockData {

    private final DButils dButils = new DButils();
    private final CustomersDB_DAO customersDBDao = new CustomersDB_DAO();
    private final CouponsDB_DAO couponsDBDao = new CouponsDB_DAO();
    private final CompaniesDB_DAO companiesDBDao = new CompaniesDB_DAO();

    /**
     * Creates links between coupons and customers for all the customers listed in the DB.  Steps:
     * 1 - check if DB contains customers
     * 2 - check if DB contains coupons
     * 3 - Link coupons and customers + update the correct amount
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean FillInCustomerVsCouponsTable() throws CouponSystemException {
        // Part 1 - check if DB contains customers
        int numberOfCustomers = countItemsInTable(Read.countNumberOfCustomers);
        if(numberOfCustomers > 0) {
            // Get all customers from DB
            ArrayList<Customer> customers = customersDBDao.GetAllCustomers();

            // Part 2 - check if DB contains categories
            if (countItemsInTable(Read.countNumberOfCategories) > 0) {

                // Part 2 - check if DB contains coupons
                if(countItemsInTable(Read.countNumberOfCoupons) > 0) {
                    ArrayList<Coupon> coupons = couponsDBDao.GetAllCoupons();

                    // Part 3 - Prepare params map (customers Vs Coupons)
                    Map<Integer, Object> params = PrepareParamsCustomersVsCoupons(coupons,customers);

                    // Part 4 - Create items in Customers_vs_coupons table
                    // Check if 'customers_vs_coupons' table is empty
                    if(countItemsInTable(Read.countCustomersVsCoupons) == 0) {
                        // Prepare multiple values SQL String
                        String insertCustVsCoupMulti = dButils.sqlInsertMultipleValues(customers.size(), SQLinsertMultipleValues.CustomerVsCoupon);
                        return dButils.runQueryWithMap(insertCustVsCoupMulti, params);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets an array list of couponIDs (integers) for customers
     * @return ArrayList<Integer> with couponIDs if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private ArrayList<Integer> GetCouponIDsForCustomers() throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        ArrayList<Integer> couponIDForCustomers = new ArrayList<>();
        params.put(1,null);
        ResultSet results = dButils.runQueryForResult(Read.getCouponIDCustomersVsCoupons,params);
        try {
            int couponID = -1;
            while (results.next()) {
                couponID = results.getInt(1);
                couponIDForCustomers.add(couponID);
            }
            return couponIDForCustomers;
        }
        catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }


    /**
     * Prepares a params map for filling in the customerVsCoupons table
     * @param coupons an array list of coupons to add to table
     * @param customers an array list of customers to add to table
     * @return Map<Integer, Object> params if succeeded, null if failed.
     */
    private Map<Integer, Object> PrepareParamsCustomersVsCoupons(ArrayList<Coupon> coupons, ArrayList<Customer> customers) {
        Map<Integer, Object> params = new HashMap<>();
        int counter = 1;
        for (Customer customer : customers) {
            int couponID = (int) (Math.random() * coupons.size()) + 1;
            params.put(counter++, customer.getId());
            params.put(counter++, couponID);
        }
        return params;
    }

    /**
     * Fills in customer table with the number of customers the user wants to enter
     * @param numberOfCustomers number of customers to insert into DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean FillInCustomerTable(int numberOfCustomers) throws CouponSystemException {
        // Prepare params Map with Company values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 1; i <= numberOfCustomers; i++) {
            params.put(counter++,"FirstName"+i);
            params.put(counter++,"LastName"+i);
            params.put(counter++,"Customer"+i+"@hotmail.com");
            params.put(counter++,"Pass"+i);
        }
        // Prepare multiple insert SQL statement
        String sql = dButils.sqlInsertMultipleValues(numberOfCustomers,SQLinsertMultipleValues.Customer);
        // Run query in DB
        if(dButils.runQueryWithMap(sql, params));
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
    public boolean FillInCategoryTable() throws CouponSystemException {
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
        String sql = dButils.sqlInsertMultipleValues(Category.values().length, SQLinsertMultipleValues.Category);

        // Run query in DB
        if(dButils.runQueryWithMap(sql, params))
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
    public boolean FillInCompanyTable(int numberOfCompanies) throws CouponSystemException {
        // Prepare params Map with Company values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 1; i <= numberOfCompanies; i++) {
            params.put(counter++,"Company"+i);
            params.put(counter++,"Company"+i+"@hotmail.com");
            params.put(counter++,"PassComp"+i);
        }
        // Prepare multiple insert SQL statement
        String sql = dButils.sqlInsertMultipleValues(numberOfCompanies, SQLinsertMultipleValues.Company);
        // Run query in DB
        if(dButils.runQueryWithMap(sql, params));
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
    public boolean CreateCouponsForAllCompanies(int numberOfCouponsePerCompany, int amountCouponsPerType,
                                                       double maxPrice) throws CouponSystemException {

        // Part 1 - check if DB contains companies
        int numOfCompanies = countItemsInTable(Read.countNumberOfCompanies);
        if(numOfCompanies > 0) {
            ArrayList<Company> companies = companiesDBDao.GetAllCompanies();

            // Part 2 - check if DB contains categories
            if(countItemsInTable(Read.countNumberOfCategories) > 0) {

                // Part 3 - create coupons in DB

                // Prepare multiple insert SQL statement
                String sql = dButils.sqlInsertMultipleValues(numberOfCouponsePerCompany, SQLinsertMultipleValues.Coupon);

                // Add coupons for each company
                for(Company company: companies) {
                    // Prepare params Map with random Coupon values
                    Map<Integer,Object> params = createMapRandomCoupons(company.getId(),
                            numberOfCouponsePerCompany,amountCouponsPerType,maxPrice);

                    // Insert coupons into DB:
                    if(dButils.runQueryWithMap(sql,params)) ;
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
     * Gets a map of all the categories listed in the DB
     * @return a map of categoryID (Integer) and name (String) if succeeded, 'null' if failed or if no categories exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Map<Integer, String> GetAllCategories() throws CouponSystemException {
        // Part 1 - Get categories - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.getAllCategories,params);

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
     * Creates a map of random coupons linked to companyID provided in param
     * @param companyID used to create the coupon
     * @param numCouponsPerComp number of coupons for each company
     * @param amountCouponsPerType amount of coupons for each type of coupon
     * @param maxPrice maximum price for each coupon
     * @return Map<Integer,Object> with params if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private Map<Integer, Object> createMapRandomCoupons(int companyID, int numCouponsPerComp,
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
    private int GetRandomCategoryIdFromMap(Map<Integer, String> categories) {
        return (int) (Math.random()*(categories.size()) )+1;
    }

    /**
     * Checks whether the DB contains items in the table specified by sql param
     * @param sql String of SQL command to send to DB (to check if table is empty)
     * @return number of items in DB table if succeeded, -1 if failed or if table is empty
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public int countItemsInTable(String sql) throws CouponSystemException {
        //Todo - convert all 'IsEmpty' functions to this one
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = dButils.runQueryForResult(sql,params);
        try {
            int numberOfItems = -1;
            while (results.next()) {
                numberOfItems = results.getInt(1);
            }
            return numberOfItems;
        }
        catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }
}
