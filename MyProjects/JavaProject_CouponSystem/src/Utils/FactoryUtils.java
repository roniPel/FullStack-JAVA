package Utils;

import Beans.Category;
import DataBase.SQLcommands;
import ErrorHandling.CouponSystemException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static DataBase.DButils.runQueryWithMap;
import static DataBase.SQLcommands.insertCategory;

public class FactoryUtils {
    public static String beautifyPrice(Double price) {
        String pattern = "###,###.##";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(price);
    }

    public static boolean FillInCategoryTable() throws CouponSystemException {
        String category, updatedSQLcommand;
        Map<Integer,Object> params = new HashMap<>();
        for (int i = 0; i < Category.values().length; i++) {
            category = String.valueOf(Category.values()[i]);
            params.put(i+1,category);
        }
        // Repeat 'insert category' SQL command in order to only run query once
        updatedSQLcommand = SQLcommands.insertCategory.repeat(Category.values().length);
        return runQueryWithMap(updatedSQLcommand, params);
    }

    public static boolean FillInCompanyTable() throws CouponSystemException {
        String company, updatedSQLcommand;
        int numberOfCompanies = 7;
        Map<Integer,Object> params = CreateRandomCompanies(numberOfCompanies);
        // Repeat 'insert company' SQL command in order to run query once in DB
        updatedSQLcommand = SQLcommands.insertCompany.repeat(numberOfCompanies);
        return runQueryWithMap(updatedSQLcommand, params);
    }

    private static Map<Integer,Object> CreateRandomCompanies(int numberOfCompanies) {
        int counter = 1;
        Map<Integer,Object> params = new HashMap<>();
        for (int i = 1; i <= numberOfCompanies; i++) {
            params.put(counter,"Company"+i);
            params.put(counter+1,"Company"+i+"@hotmail.com");
            params.put(counter+2,"PassComp"+i);
            counter += 3;
        }
        return params;
    }

}
