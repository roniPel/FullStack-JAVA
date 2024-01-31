package DataBase.DAO.DB_DAO;

import Beans.Company;
import Beans.Coupon;
import DataBase.DAO.CompaniesDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
import ErrorHandling.CouponSystemException;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ErrorHandling.Errors.SQL_ERROR;

public class CompaniesDB_DAO implements CompaniesDAO {
    private ConnectionPool connectionPool;

    /**
     * Checks whether a company exists in the DB
     * @param email company's email
     * @param password company's password
     * @return true if company exists, false if company doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean IsCompanyExists(String email, String password) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,email);
        params.put(2,password);
        ResultSet result = DataBase.DButils.runQueryForResult(DataBase.CRUD.Read.companyExists, params);
        int numberOfReturns = 0;
        try {
            if(result.next()) {
                numberOfReturns = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        if(numberOfReturns == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds a company to the DB - adds the company and the company's coupons (according to the param provided)
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean AddCompany(Company company) throws CouponSystemException {
        // Part 1 - Add a new company in DB
        Map<Integer,Object> params = new HashMap<>();
        // ID in company item is ignored.  DB creates an ID automatically
        params.put(1,company.getName());
        params.put(2,company.getEmail());
        params.put(3,company.getPassword());

        if(DButils.runQueryWithMap(DataBase.CRUD.Create.insertCompany,params) ) {
            if(company.getCoupons() == null){
                return true;
            }
            // Part 2 - Iterate over coupon list and add each company coupon to DB
            for(Coupon coupon: company.getCoupons()) {
                // Create new coupon in DB:
                if(CouponsDB_DAO.AddCoupon(coupon)) {
                    continue;
                }
                else {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Updates a company in the DB - updates the company's details (according to the company ID, based on param provided)
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean UpdateCompany(Company company) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,company.getName());
        params.put(2,company.getEmail());
        params.put(3,company.getPassword());
        params.put(4,company.getId());

        if(DButils.runQueryWithMap(DataBase.CRUD.Update.updateCompany,params) ) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets an ArrayList of all the companies listed in the DB
     * @return an ArrayList of 'Company' class items if succeeded, 'null' if failed or if no companies exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static ArrayList<Company> GetAllCompanies() throws CouponSystemException {
        // Part 1 - Get companies - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(DataBase.CRUD.Read.getAllCompanies,params);

        // Part 2 - add results to company list
        ArrayList<Company> companyList = new ArrayList<>();

        try {
            while (results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                String email = results.getString(3);
                String password = results.getString(4);

                // Todo - Get linked coupons from DB
                // Convert SQL array to Coupons array
                ArrayList<Coupon> coupons = null;
                //ArrayList<Coupon> coupons = new ArrayList(List.of(couponSQLarray));

                // Create a new Company object in the companyList
                companyList.add(new Company(id, name, email, password, coupons));
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return companyList;
    }

    /**
     * Gets a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static Company GetOneCompany(int companyID) throws CouponSystemException {
        // Part 1 - Get company - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,companyID);
        ResultSet results = DButils.runQueryForResult(DataBase.CRUD.Read.getOneCompany,params);

        // Part 2 - organize results in a company variable
        try {
            while(results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                String email = results.getString(3);
                String password = results.getString(4);

                // Todo - Get linked coupons from DB
                // Convert SQL array to Coupons array
                ArrayList<Coupon> coupons = null;
                //ArrayList<Coupon> coupons = new ArrayList(List.of(couponSQLarray));

                // return a Company object with all details
                return new Company(id, name, email, password, coupons);
            }
            // If no data is in the result set, return null
            return null;
        } catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
    }
}
