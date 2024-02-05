package DataBase.DAO.DB_DAO;

import Beans.Category;
import Beans.Coupon;
import DataBase.CRUD.Create;
import DataBase.CRUD.Delete;
import DataBase.CRUD.Read;
import DataBase.DAO.CouponsDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static DataBase.DButils.*;
import static ErrorHandling.Errors.SQL_ERROR;

public class CouponsDB_DAO implements CouponsDAO {
    private ConnectionPool connectionPool;
    //Todo - test all class methods

    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean AddCoupon(Coupon coupon) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = PrepareParamsForAddCoupon(coupon);
        // Part 2 - create coupon in DB
        return DButils.runQueryWithMap(DataBase.CRUD.Create.insertCoupon, params);
    }


    /**
     * Prepares 'param' map for adding coupon to DB
     * @param coupon coupon to be added to DB
     * @return Map<Integer, Object> params if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static Map<Integer, Object> PrepareParamsForAddCoupon(Coupon coupon) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        // Part 1 - Get coupon's categoryID from DB:
        int categoryID = getCouponCategoryID(coupon.getCategory().toString());
        if(categoryID>0) {
            // Part 2 - prepare parameters
            params.put(1, coupon.getCompanyID());
            params.put(2, categoryID);
            params.put(3, coupon.getTitle());
            params.put(4, coupon.getDescription());
            params.put(5, coupon.getStartDate());
            params.put(6, coupon.getEndDate());
            params.put(7, coupon.getAmount());
            params.put(8, coupon.getPrice());
            params.put(9, coupon.getImage());
        }
        return params;
    }


    /**
     * Provides coupon's category ID from DB - based on the details listed in the param
     * @param categoryName a String containing category name to search for in DB
     * @return int with category ID if succeeded, -1 if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static int getCouponCategoryID(String categoryName) throws CouponSystemException {
        int categoryID;
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,categoryName);
        ResultSet result = DataBase.DButils.runQueryForResult(DataBase.CRUD.Read.getCategoryID, params);
        try {
            while(result.next()) {
                categoryID = result.getInt(1);
                return categoryID;
            }
            return -1;
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
    }


    /**
     * Updates a coupon in the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean UpdateCoupon(Coupon coupon) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = PrepareParamsForAddCoupon(coupon);
        params.put(10,coupon.getId());
        // Part 2 - update coupon in DB
        return runQueryWithMap(DataBase.CRUD.Update.updateCoupon, params);
    }


    /**
     * Deletes a coupon from the DB - based on the couponID listed in the param
     * @param couponID a couponID to be deleted
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean DeleteCoupon(int couponID) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,couponID);
        // Part 2 - delete coupon from DB
        return runQueryWithMap(Delete.deleteCoupon,params);
    }


    /**
     * Get all the coupons listed in DB
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static ArrayList<Coupon> GetAllCoupons() throws CouponSystemException {

        // Part 1 - Get coupons - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(Read.getAllCoupons,params);

        // Get category table from DB - for use in part 2
        Map<Integer, String> categories = DB_DAO_MockData.GetAllCategories();

        // Part 2 - add results to coupon list
        return MapCouponsFromResultSet(results);
    }


    /**
     * Get one coupon from DB based on couponID provided in params
     * @param couponID Id for coupon to get from DB
     * @return Coupon object if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Coupon GetOneCoupon(int couponID) throws CouponSystemException {
        // Part 1 - Get coupon - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,couponID);
        ResultSet results = DButils.runQueryForResult(Read.getCouponsById,params);

        // Part 2 - add results to coupon list
        ArrayList<Coupon> coupons = MapCouponsFromResultSet(results);
        if(coupons.size() == 1){
            return coupons.get(0);
        }
        else {
            return null;
        }
    }


    /**
     * Create an arrayList of coupon objects from resultSet from DB
     * @param results ResultSet with results from DB query
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static ArrayList<Coupon> MapCouponsFromResultSet(ResultSet results) throws CouponSystemException {
        Map<Integer, String> categories = DB_DAO_MockData.GetAllCategories();
        ArrayList<Coupon> coupons = new ArrayList<>();
        try {
            while (results.next()) {
                int id = results.getInt(1);
                int companyId = results.getInt(2);
                int categoryId = results.getInt(3);
                String title = results.getString(4);
                String description = results.getString(5);
                LocalDate startDate = results.getDate(6).toLocalDate();
                LocalDate endDate = results.getDate(7).toLocalDate();
                int amount = results.getInt(8);
                double price = results.getDouble(9);
                String image = results.getString(10);

                // Create a new coupon object and add to ArrayList
                coupons.add( new Coupon(id, companyId, Category.valueOf(categories.get(categoryId)),
                        title, description, startDate, endDate, amount, price, image) );
            }
            return coupons;
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
    }


    /**
     * Adds a coupon purchase in the DB - based on the details listed in the param
     * @param couponID coupon ID to be linked to customer
     * @param customerID customer ID to be linked to coupon
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean AddCouponPurchase(int customerID, int couponID) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = PrepareParamsMapCouponPurchaseDel(customerID,couponID);
        // Part 2 - Add coupon purchase in DB
        return runQueryWithMap(Create.insertCustomerVsCoupon,params);
    }


    /**
     * Deletes a coupon purchase in the DB - based on the details listed in the param
     * @param couponID coupon ID linked to customer
     * @param customerID customer ID linked to coupon
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean DeleteCouponPurchase(int customerID, int couponID) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = PrepareParamsMapCouponPurchaseDel(customerID,couponID);
        // Part 2 - delete coupon purchase in DB
        return runQueryWithMap(Delete.deleteCouponPurchase,params);
    }


    /**
     * Prepares a params map for coupon purchase and coupon delete actions
     * @param customerID customer ID to insert into map
     * @param couponID coupon ID to insert into map
     * @return Map<Integer, Object> of parameters (customerID, CouponID) if succeeded, null if failed.
     */
    private static Map<Integer, Object> PrepareParamsMapCouponPurchaseDel(int customerID, int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,customerID);
        params.put(2,couponID);
        return params;
    }

    /**
     * Get all the coupons listed in DB for a specific company
     * @param companyID ID belonging to the company the coupons belong to
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static ArrayList<Coupon> GetCouponsForCompany(int companyID) throws CouponSystemException {
        // Part 1 - Get coupons - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,companyID);
        ResultSet results = DButils.runQueryForResult(Read.getCouponsForCompany,params);

        // Get category table from DB - for use in part 2
        Map<Integer, String> categories = DB_DAO_MockData.GetAllCategories();

        // Part 2 - add results to coupon list
        return AddResultsToCouponList(results);
    }


    /**
     * Get all the coupons listed in DB for a specific customer
     * @param customerID ID belonging to the customer the coupons belong to
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static ArrayList<Coupon> GetCouponsForCustomer(int customerID) throws CouponSystemException {
        // Part 1 - - Get coupons ID map - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,customerID);
        ResultSet results = DButils.runQueryForResult(DataBase.CRUD.Read.getCouponsForCustomer,params);

        // Part 2 -  prepare params and SQL command - to send to DB query in next part
        params.clear();
        params = PrepareParamsMapFromResultSet(results);
        String sql = sqlInsertMultiple_IN_Values(Read.getCouponsById,params.size());

        // Part 3 - Get coupons list - query from DB (based on couponID list)
        if(params.isEmpty()){
            return null;
        }
        results = DButils.runQueryForResult(sql,params);

        // Part 4 - add results to couponID list
        return AddResultsToCouponList(results);
    }


    /**
     * Converts a result set from SQL DB to an Array list of coupon objects
     * @param results result set containing all Coupons from DB
     * @return ArrayList<Coupon> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static ArrayList<Coupon> AddResultsToCouponList(ResultSet results) throws CouponSystemException {
        ArrayList<Coupon> couponList = new ArrayList<>();

        // Get category table from DB - for use in part 2
        Map<Integer, String> categories = DB_DAO_MockData.GetAllCategories();

        // Insert results into couponList array
        try {
            while (results.next()) {
                int id = results.getInt(1);
                int companyId = results.getInt(2);
                int categoryId = results.getInt(3);
                String title = results.getString(4);
                String description = results.getString(5);
                LocalDate startDate = results.getDate(6).toLocalDate();
                LocalDate endDate = results.getDate(7).toLocalDate();
                int amount = results.getInt(8);
                double price = results.getDouble(9);
                String image = results.getString(10);

                // Create a new coupon object in the couponList
                couponList.add(new Coupon(id,companyId,Category.valueOf(categories.get(categoryId)),
                        title,description,startDate,endDate,amount,price,image));
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return couponList;
    }


    /**
     * Converts a result set from SQL DB to a map of coupon IDs to use as parameters for a DB query
     * @param results result set containing coupon IDs needed to use as parameters in params map
     * @return Map<Integer, Object> of parameters (Counter, CouponID) if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static Map<Integer, Object> PrepareParamsMapFromResultSet(ResultSet results) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        try {
            // Iterate and add results to coupons map
            while (results.next()) {
                int customerId = results.getInt(1);
                int couponId = results.getInt(2);
                params.put(counter++,couponId);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
        return params;
    }
}
