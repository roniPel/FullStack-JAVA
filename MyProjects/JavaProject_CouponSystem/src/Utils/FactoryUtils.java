package Utils;

import Beans.Category;
import ErrorHandling.CouponSystemException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static DataBase.DButils.runQueryWithMap;

public class FactoryUtils {

    /**
     * Changes price format into organized decimal format
     * @param price - price in double format
     * @return - price in String format
     */
    public static String beautifyPrice(Double price) {
        String pattern = "###,###.##";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(price);
    }

    /**
     * Fills in category table with all categories in 'Category' Enum
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    public static boolean FillInCategoryTable() throws CouponSystemException {
        String category;
        Map<Integer,Object> params = new HashMap<>();
        for (int i = 0; i < Category.values().length; i++) {
            category = String.valueOf(Category.values()[i]);
            params.put(1,category);
            if(runQueryWithMap(DataBase.CRUD.Create.insertCategory, params));
            else {
                System.out.println("There was a problem creating the Category table");
                return false;
            }
        }
        return true;
    }

    /**
     * Fills in company table with the number of companies the user wants to enter
     * @param numberOfCompanies - number of companies to insert into DB
     * @return - true if succeeded, false if failed.
     * @throws CouponSystemException - If we get any SQL exception.  Details are provided
     */
    public static boolean FillInCompanyTable(int numberOfCompanies) throws CouponSystemException {
        Map<Integer,Object> params = new HashMap<>();
        for (int i = 1; i <= numberOfCompanies; i++) {
            params.put(1,"Company"+i);
            params.put(2,"Company"+i+"@hotmail.com");
            params.put(3,"PassComp"+i);
            if(runQueryWithMap(DataBase.CRUD.Create.insertCompany, params));
            else {
                System.out.println("There was a problem filling the company table. ");
                return false;
            }
        }
        System.out.println("Company table has been auto filled. ");
        return true;
    }
}
