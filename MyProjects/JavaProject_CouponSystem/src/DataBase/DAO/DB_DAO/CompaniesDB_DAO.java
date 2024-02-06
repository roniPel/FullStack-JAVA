package DataBase.DAO.DB_DAO;

import Beans.Company;
import Beans.Coupon;
import DataBase.CRUD.Delete;
import DataBase.CRUD.Read;
import DataBase.DAO.CompaniesDAO;
import DataBase.ConnectionPool;
import DataBase.DButils;
import DataBase.SQLinsertMultipleValues;
import ErrorHandling.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static DataBase.DAO.DB_DAO.CouponsDB_DAO.PrepareParamsForAddCoupons;
import static DataBase.DButils.*;
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
    public boolean IsCompanyExists(String email, String password) throws CouponSystemException {
        // Part 1 - prepare params
        Map<Integer,Object> params = PrepareParamsForLoginCheck(email,password);
        // Part 2 - run query for results in DB
        ResultSet results = DataBase.DButils.runQueryForResult(DataBase.CRUD.Read.isCompanyExists, params);
        // Part 3 - check results
        return DButils.CheckLoginResults(results);
    }


    /**
     * Checks whether a company exists in the DB
     * @param companyID company's id
     * @return true if company exists, false if company doesn't exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean IsCompanyIdExists(int companyID) throws CouponSystemException {
        // Part 1 - prepare params
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,companyID);
        // Part 2 - run query for results in DB
        ResultSet results = DataBase.DButils.runQueryForResult(Read.isCompanyIdExists, params);
        // Part 3 - check results
        return DButils.CheckLoginResults(results);
    }

    /**
     * Checks whether a company name exists in the DB
     * @param name company's name
     * @return true if company name exists, false if company doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean IsCompanyNameExists(String name) throws CouponSystemException {
        // Part 1 - prepare params
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,name);
        // Part 2 - run query for results in DB
        ResultSet results = DataBase.DButils.runQueryForResult(Read.isCompanyNameExists, params);
        // Part 3 - check results
        return DButils.CheckLoginResults(results);
    }

    /**
     * Checks whether a company email exists in the DB
     * @param email company's email
     * @return true if company email exists, false if company doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean IsCompanyEmailExists(String email) throws CouponSystemException {
        // Part 1 - prepare params
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,email);
        // Part 2 - run query for results in DB
        ResultSet results = DataBase.DButils.runQueryForResult(Read.isCompanyEmailExists, params);
        // Part 3 - check results
        return DButils.CheckLoginResults(results);
    }

    /**
     * Adds a company to the DB
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean AddCompany(Company company) throws CouponSystemException {
        // Part 1 - Prepare Hashmap + insert company into DB
        ArrayList<Company> companies = new ArrayList<>();
        companies.add(company);
        Map<Integer,Object> params = PrepareParamsForAddCompany(companies);
        if(DButils.runQueryWithMap(DataBase.CRUD.Create.insertCompany,params) ) {
            if(company.getCoupons() == null){   // No coupons associated with company
                return true;
            }
            // Part 2 - prepare a multi statement and insert coupons to DB
            String sql = DButils.sqlInsertMultipleValues(company.getCoupons().size(), SQLinsertMultipleValues.Coupon);
            params.clear();
            params = CouponsDB_DAO.PrepareParamsForAddCoupons(company.getCoupons());
            // Insert coupons into DB:
            if(runQueryWithMap(sql,params)) ;
            else {  // If coupons were not added to DB - query failed
                return false;
            }
        }
        return true;
    }

    /**
     * Prepares 'param' map for adding customers to DB
     * @param companies an array list of companies to be added to params map
     * @return Map<Integer, Object> params if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private Map<Integer, Object> PrepareParamsForAddCompany(ArrayList<Company> companies) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        int count = 1;
        for(Company company: companies) {
            params.put(count++,company.getName());
            params.put(count++,company.getEmail());
            params.put(count++,company.getPassword());
        }
        return params;
    }

    /**
     * Updates a company in the DB
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean UpdateCompany(Company company) throws CouponSystemException {
        // Part 1 - Prepare params hashmap
        ArrayList<Company> companies = new ArrayList<>();
        companies.add(company);
        Map<Integer,Object> params = PrepareParamsForAddCompany(companies);
        params.put(4,company.getId());
        // Part 2 - Update company in DB
        return DButils.runQueryWithMap(DataBase.CRUD.Update.updateCompany, params);
        // Todo - Part 3 - update company coupons in DB?
    }

    /**
     * Deletes a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean DeleteCompany(int companyID) throws CouponSystemException {
        // Part 1 - prepare params map
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,companyID);
        // Part 2 - delete company from DB
        return runQueryWithMap(Delete.deleteCompany,params);
        //Todo Part 3 - delete associated coupons in DB?
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
        return ConvertResultSetToCompanyArray(results);
    }

    /**
     * Gets a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public Company GetOneCompany(int companyID) throws CouponSystemException {
        // Part 1 - Get company - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,companyID);
        ResultSet results = DButils.runQueryForResult(DataBase.CRUD.Read.getOneCompany,params);

        // Part 2 - organize results in a company variable
        ArrayList<Company> companies = ConvertResultSetToCompanyArray(results);
        if(companies.size() == 1){
            return companies.get(0);
        }
        return null;
    }

    /**
     * Converts a result set from SQL DB to an Array list of company objects
     * @param results result set containing all Companies from DB
     * @return ArrayList<Company> if succeeded, null if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static ArrayList<Company> ConvertResultSetToCompanyArray(ResultSet results) throws CouponSystemException {
        ArrayList<Company> companies = new ArrayList<>();
        try {
            while(results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                String email = results.getString(3);
                String password = results.getString(4);

                //Todo - Delete section so that company has 'null' coupons
                // Convert SQL array to Coupons array
                ArrayList<Coupon> coupons = CouponsDB_DAO.GetCouponsForCompany(id);

                // add a Company object with all details to companies array
                companies.add(new Company(id, name, email, password, coupons) );
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        }
        return companies;
    }
}
