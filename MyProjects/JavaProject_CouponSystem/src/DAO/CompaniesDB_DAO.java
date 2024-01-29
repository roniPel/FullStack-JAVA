package DAO;

import Beans.Company;
import Beans.Coupon;
import DataBase.ConnectionPool;
import DataBase.DButils;
import DataBase.SQLcommands;
import ErrorHandling.CouponSystemException;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ErrorHandling.Errors.SQL_ERROR;

public class CompaniesDB_DAO implements CompaniesDAO{
    private ConnectionPool connectionPool;

    public static boolean IsCompanyExists(String email, String password) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,email);
        params.put(2,password);
        ResultSet result = DataBase.DButils.runQueryForResult(SQLcommands.companyExists, params);
        int numberOfReturns = 0;
        try {
            if(result.next()) {
                numberOfReturns = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        if(numberOfReturns == 1) {
            System.out.println("Company exists");
            return true;
        }
        else {
            System.out.println("Company does not exist /" +
                    " there is a problem with the company listing / " +
                    "no company matches the inserted details.");
            return false;
        }
    }

    public static boolean AddCompany(Company company) throws CouponSystemException {
        // Part 1 - Add a new company in DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,company.getId());
        params.put(2,company.getName());
        params.put(3,company.getEmail());
        params.put(4,company.getPassword());

        if(DButils.runQueryWithMap(SQLcommands.insertCompany,params) ) {
            if(company.getCoupons() == null){
                System.out.println("Company was added to DB. There were no coupons associated with the company. ");
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
            System.out.println("Company and relevant coupons were added successfully to DB. ");
            return true;
        }
        else {
            System.out.println("There was a problem adding the company to DB. ");
            return false;
        }
    }

    public static boolean UpdateCompany(Company company) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,company.getName());
        params.put(2,company.getEmail());
        params.put(3,company.getPassword());
        params.put(4,company.getId());

        if(DButils.runQueryWithMap(SQLcommands.updateCompany,params) ) {
            System.out.println("Company was updated successfully");
            return true;
        }
        else {
            System.out.println("There was a problem updating the company.");
            return false;
        }
    }

    public ArrayList<Company> GetAllCompanies() throws CouponSystemException {
        // Part 1 - Get companies - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,null);
        ResultSet results = DButils.runQueryForResult(SQLcommands.getAllCompanies,params);

        // Part 2 - add results to company list
        ArrayList<Company> companyList = new ArrayList<>();

        try {
            while (results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                String email = results.getString(3);
                String password = results.getString(4);
                Array couponSQLarray = results.getArray(5);
                // Convert SQL array to Coupons array
                ArrayList<Coupon> coupons = new ArrayList(List.of(couponSQLarray));
                // Create a new Company object in the companyList
                companyList.add(new Company(id, name, email, password, coupons));
            }
        }
        catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return companyList;
    }

    public Company GetOneCompany(int companyID) throws CouponSystemException {
        // Part 1 - Get company - query from DB
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,companyID);
        ResultSet results = DButils.runQueryForResult(SQLcommands.getOneCompany,params);

        // Part 2 - organize results in a company variable
        try {
            int id = results.getInt(1);
            String name = results.getString(2);
            String email = results.getString(3);
            String password = results.getString(4);
            Array couponSQLarray = results.getArray(5);
            // Convert SQL array to Coupons array
            ArrayList<Coupon> coupons = new ArrayList(List.of(couponSQLarray));
            // return a Company object with all details
            return new Company(id, name, email, password, coupons);
        } catch(SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
    }
}