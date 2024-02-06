package DataBase.DAO;

import Beans.Company;
import ErrorHandling.CouponSystemException;

import java.util.ArrayList;

public interface CompaniesDAO {
    /**
     * Checks whether a company exists in the DB
     * @param email company's email
     * @param password company's password
     * @return true if company exists, false if company doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean IsCompanyExists(String email, String password) throws CouponSystemException{ return false;};


    /**
     * Adds a company to the DB - adds the company and the company's coupons (according to the param provided)
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean AddCompany(Company company) throws CouponSystemException{return false;};

    /**
     * Deletes a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean DeleteCompany(int companyID) throws CouponSystemException {return false;}


    /**
     * Updates a company in the DB - updates the company's details (according to the company ID, based on param provided)
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static boolean UpdateCompany(Company company) throws CouponSystemException {return false;};


    /**
     * Gets an ArrayList of all the companies listed in the DB
     * @return an ArrayList of 'Company' class items if succeeded, 'null' if failed or if no companies exist.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static ArrayList<Company> GetAllCompanies() throws CouponSystemException {return null;};


    /**
     * Gets a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    static Company GetOneCompany(int companyID) throws CouponSystemException {return null;};
}
