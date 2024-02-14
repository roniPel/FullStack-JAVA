package DataBase.DAO.DB_DAO;

import Beans.Category;
import Beans.Coupon;
import DataBase.CRUD.Create;
import DataBase.CRUD.Delete;
import DataBase.CRUD.Read;
import DataBase.DAO.CouponsDAO;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static ErrorHandling.Errors.SQL_ERROR;

/**
 * Class used for sending coupons CRUD actions to DB
 */
public class CouponsDB_DAO implements CouponsDAO {
    private final DButils dButils = new DButils();


    /**
     * Adds a coupon to the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean AddCoupon(Coupon coupon) throws CouponSystemException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);
        // Part 1 - prepare params map
        Map<Integer, Object> params = PrepareParamsForAddCoupons(coupons);
        // Part 2 - create coupon in DB

        return dButils.runQueryWithMap(DataBase.CRUD.Create.insertCoupon, params);
    }


    /**
     * Prepares 'param' map for adding coupons to DB
     * @param coupons an array list of coupons to be added to params map
     * @return A map of integers and objects named 'params' if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Map<Integer, Object> PrepareParamsForAddCoupons(ArrayList<Coupon> coupons) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        int count = 1;
        // Part 1 - Get coupon's categoryID from DB:
        for(Coupon coupon: coupons) {
            int categoryID = getCouponCategoryID(coupon.getCategory().toString());
            if (categoryID > 0) {
                // Part 2 - prepare parameters
                params.put(count++, coupon.getCompanyID());
                params.put(count++, categoryID);
                params.put(count++, coupon.getTitle());
                params.put(count++, coupon.getDescription());
                params.put(count++, coupon.getStartDate());
                params.put(count++, coupon.getEndDate());
                params.put(count++, coupon.getAmount());
                params.put(count++, coupon.getPrice());
                params.put(count++, coupon.getImage());
            }
        }
        return params;
    }

    /**
     * Returns a coupon's ID based on title (unique)
     * @param title coupon's title
     * @return couponID if coupon exists, -1 if coupon doesn't exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public int GetCouponIDByTitle(String title) throws CouponSystemException {
        // Part 1 - Prepare params
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,title);
        // Part 2 - run query for results in DB
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.getCouponIdByTitle,params);
        // Part 3 - check results and return couponID
        int couponID = -1;
        try {
            while(results.next()) {
                couponID = results.getInt(1);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR);
        }
        return couponID;
    }


    /**
     * Provides coupon's category ID from DB - based on the details listed in the param
     * @param categoryName a String containing category name to search for in DB
     * @return int with category ID if succeeded, -1 if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private int getCouponCategoryID(String categoryName) throws CouponSystemException {
        int categoryID;
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,categoryName);
        ResultSet result = dButils.runQueryForResult(DataBase.CRUD.Read.getCategoryID, params);
        try {
            while(result.next()) {
                categoryID = result.getInt(1);
                return categoryID;
            }
            return -1;
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR);
        }
    }


    /**
     * Updates a coupon in the DB - based on the details listed in the param
     * @param coupon a 'Coupon' class instance containing coupon details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean UpdateCoupon(Coupon coupon) throws CouponSystemException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);
        // Part 1 - prepare params map
        Map<Integer, Object> params = PrepareParamsForAddCoupons(coupons);
        params.put(10,coupon.getId());
        // Part 2 - update coupon in DB
        return dButils.runQueryWithMap(DataBase.CRUD.Update.updateCoupon, params);
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
        return dButils.runQueryWithMap(Delete.deleteCoupon,params);
    }


    /**
     * Get all the coupons listed in DB
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> GetAllCoupons() throws CouponSystemException {

        // Part 1 - Get coupons - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = dButils.runQueryForResult(Read.getAllCoupons,params);

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
        ResultSet results = dButils.runQueryForResult(Read.getCouponsById,params);

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
    private ArrayList<Coupon> MapCouponsFromResultSet(ResultSet results) throws CouponSystemException {
        Map<Integer, String> categories = GetAllCategories();
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
            throw new CouponSystemException(SQL_ERROR);
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
        Map<Integer, Integer> customerVsCouponsMap = new HashMap<>();
        customerVsCouponsMap.put(customerID,couponID);
        Map<Integer, Object> params = PrepareParamsMapCouponPurchaseDel(customerVsCouponsMap);
        // Part 2 - Add coupon purchase in DB
        return dButils.runQueryWithMap(Create.insertCustomerVsCoupon,params);
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
        Map<Integer, Integer> customerVsCouponsMap = new HashMap<>();
        customerVsCouponsMap.put(customerID,couponID);
        Map<Integer, Object> params = PrepareParamsMapCouponPurchaseDel(customerVsCouponsMap);
        // Part 2 - delete coupon purchase in DB
        return dButils.runQueryWithMap(Delete.deleteCouponPurchase,params);
    }


    /**
     * Prepares a params map for coupon purchase and coupon delete actions
     * @param customerVsCouponsMap  map of customer IDs Vs coupon IDs to insert into params map
     * @return Map<Integer, Object> of parameters if succeeded, null if failed.
     */
    static Map<Integer, Object> PrepareParamsMapCouponPurchaseDel(Map<Integer, Integer> customerVsCouponsMap) {
        Map<Integer, Object> params = new HashMap<>();
        int count = 1;
        for(Integer customerID : customerVsCouponsMap.keySet()){
            params.put(count++,customerID);
            params.put(count++,customerVsCouponsMap.get(customerID));
        }
        return params;
    }

    /**
     * Get all the coupons listed in DB for a specific company
     * @param companyID ID belonging to the company the coupons belong to
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> GetCouponsForCompany(int companyID) throws CouponSystemException {
        // Part 1 - Get coupons - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,companyID);
        ResultSet results = dButils.runQueryForResult(Read.getCouponsForCompany,params);

        // Part 2 - add results to coupon list
        return AddResultsToCouponList(results);
    }


    /**
     * Get all the coupons listed in DB for a specific customer
     * @param customerID ID belonging to the customer the coupons belong to
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> GetCouponsForCustomer(int customerID) throws CouponSystemException {
        // Part 1 - - Get coupons ID map - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,customerID);
        ResultSet results = dButils.runQueryForResult(DataBase.CRUD.Read.getCouponsForCustomer,params);

        // Part 2 -  prepare params and SQL command - to send to DB query in next part
        params.clear();
        params = PrepareParamsMapFromResultSet(results);
        String sql = dButils.sqlInsertMultiple_IN_Values(Read.getCouponsById,params.size());

        // Part 3 - Get coupons list - query from DB (based on couponID list)
        if(params.isEmpty()){
            return null;
        }
        results = dButils.runQueryForResult(sql,params);

        // Part 4 - add results to couponID list
        return AddResultsToCouponList(results);
    }


    /**
     * Converts a result set from SQL DB to an Array list of coupon objects
     * @param results result set containing all Coupons from DB
     * @return coupons ArrayList if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ArrayList<Coupon> AddResultsToCouponList(ResultSet results) throws CouponSystemException {
        ArrayList<Coupon> couponList = new ArrayList<>();

        // Get category table from DB - for use in part 2
        Map<Integer, String> categories = GetAllCategories();

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
            throw new CouponSystemException(SQL_ERROR);
        }
        return couponList;
    }


    /**
     * Converts a result set from SQL DB to a map of coupon IDs to use as parameters for a DB query
     * @param results result set containing coupon IDs needed to use as parameters in params map
     * @return A map of integers and integers of parameters (Counter, CouponID) if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Map<Integer, Object> PrepareParamsMapFromResultSet(ResultSet results) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        try {
            // Iterate and add results to coupons map
            while (results.next()) {
                int couponId = results.getInt(2);
                params.put(counter++,couponId);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR);
        }
        return params;
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
            throw new CouponSystemException(SQL_ERROR);
        }
        return categories;
    }
}
