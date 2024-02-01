package Utils;

import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import ErrorHandling.CouponSystemException;

public class Helper {
    //Todo - Create reports for tester/ main


    /**
     * Fills in all DB tables with mock data based on specifications in params
     * @param numCompanies Number of companies
     * @param amountPerType Amount of each type of coupon
     * @param couponsPerComp Amount of coupons per company
     * @param maxPrice Maximum coupon price (price is created using the 'Math.random()' method)
     * @return true if succeeded, false if failed
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean FillDataBaseWithMockData
            (int numCompanies,int couponsPerComp, int amountPerType, double maxPrice) throws CouponSystemException {
        // Fill in category table
        if(DB_DAO_MockData.FillInCategoryTable());
        else {
            return false;
        }
        // Fill in company table
        if(DB_DAO_MockData.FillInCompanyTable(numCompanies));
        else {
            return false;
        }
        // Fill in coupons table
        if(DB_DAO_MockData.CreateCouponsForAllCompanies(couponsPerComp,amountPerType,maxPrice));
        else {
            return false;
        }
        // Todo - Fill in customers table

        // Todo - Fill in customers_vs_coupons table

        return true;
    }
}
