package Facade;

import DataBase.DAO.CompaniesDAO;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.CustomersDAO;
import ErrorHandling.CouponSystemException;

/**
 * Abstract client Facade - for creating all facades
 */
public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDAO couponsDAO;

    /**
     * Checks whether a user exists in the DB
     * @param email user's email
     * @param password user's password
     * @return true if user exists, false if user doesn't exist or if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    public abstract boolean Login(String email, String password) throws CouponSystemException;
}
