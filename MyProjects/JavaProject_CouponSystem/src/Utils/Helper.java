package Utils;

import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import ErrorHandling.CouponSystemException;

public class Helper {
    private DB_DAO_MockData mockData = new DB_DAO_MockData();
    //Todo - Create reports for tester/ main

    //Todo - change FillDataBaseWithMockData method details + params - receive as hashmap?

    /**
     * Fills in all DB tables with mock data based on specifications in params
     * @param numCompanies Number of companies
     * @param amountPerType Amount of each type of coupon
     * @param couponsPerComp Amount of coupons per company
     * @param maxPrice Maximum coupon price (price is created using the 'Math.random()' method)
     * @return true if succeeded, false if failed
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public boolean FillDataBaseWithMockData
            (int numCompanies,int couponsPerComp, int amountPerType, double maxPrice, int numberOfCustomers) throws CouponSystemException {
        // Fill in category table
        if(mockData.FillInCategoryTable());
        else {
            return false;
        }
        // Fill in company table
        if(mockData.FillInCompanyTable(numCompanies));
        else {
            return false;
        }
        // Fill in coupons table
        if(mockData.CreateCouponsForAllCompanies(couponsPerComp,amountPerType,maxPrice));
        else {
            return false;
        }
        // Fill in customers table
        if(mockData.FillInCustomerTable(numberOfCustomers));
        else {
            return false;
        }
        // Fill in customersVScoupons table
        if(mockData.FillInCustomerVsCouponsTable()) {
            return true;
        }
        return false;
    }
}