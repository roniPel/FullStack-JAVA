package JavaProject.CouponSystem2_Spring.Services.CompanyService;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;

import java.util.ArrayList;

public interface CompaniesService {

    /**
     * Checks whether a company exists in the DB
     * @param email company's email
     * @param password company's password
     * @return true if company exists, false if company doesn't exist or if the email + password combo are incorrect.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean IsCompanyExists(String email, String password) throws CompanyException;


    /**
     * Returns a company's ID based on email (unique)
     * @param email company's email
     * @return companyID if company exists, -1 if company doesn't exist.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    int GetCompanyIDByEmail(String email) throws CompanyException;


    /**
     * Checks whether a company exists in the DB
     * @param companyID company's id
     * @return true if company exists, false if company doesn't exist.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean IsCompanyIdExists(int companyID) throws CompanyException;


    /**
     * Adds a company to the DB - adds the company and the company's coupons (according to the param provided)
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean AddCompany(Company company) throws CompanyException;


    /**
     * Deletes a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return true if succeeded, false if failed.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean DeleteCompany(int companyID) throws CompanyException;


    /**
     * Updates a company in the DB - updates the company's details (according to the company ID, based on param provided)
     * @param company a 'Company' class instance containing company details
     * @return true if succeeded, false if failed.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    boolean UpdateCompany(Company company) throws CompanyException;


    /**
     * Gets an ArrayList of all the companies listed in the DB
     *
     * @return an ArrayList of 'Company' class items if succeeded, 'null' if failed or if no companies exist.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    ArrayList<Company> GetAllCompanies() throws CompanyException;


    /**
     * Gets a company (according to the company ID provided)
     * @param companyID a company's ID, as listed in the DB
     * @return a 'Company' class item if succeeded, 'null' if failed or if no company matches the requirements.
     * @throws CompanyException  If we get any exception.  Details are provided
     */
    Company GetOneCompany(int companyID) throws CompanyException;
}
