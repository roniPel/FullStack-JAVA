package Test;

import Beans.Company;
import Beans.Coupon;
import Beans.Customer;

import java.util.ArrayList;

/**
 * Methods class - contains methods and variables used for testing system
 */
public class Methods {
    public double maxPrice = 200;
    public int GetrandInt(int range) {
        return (int)(Math.random()*(range));
    }

    public int GetRandIdFromArray(ArrayList<Integer> idList) {
        int randIdx = (int)(Math.random()*(idList.size()));
        return idList.get(randIdx);
    }

    public int GetRandIdFromCustomerArray(ArrayList<Customer> customers) {
        int randIdx = (int)(Math.random()*(customers.size()));
        if(customers.size() == 1){
            randIdx = 0;
        }
        return customers.get(randIdx).getId();
    }

    public int GetRandIdFromCompanyArray(ArrayList<Company> companies) {
        int randIdx = (int)(Math.random()*(companies.size()));
        if(companies.size() == 1){
            randIdx = 0;
        }
        return companies.get(randIdx).getId();
    }

    public int GetRandIdFromCouponsArray(ArrayList<Coupon> coupons) {
        int randIdx = (int)(Math.random()*(coupons.size()));
        if(coupons.size() == 1){
            randIdx = 0;
        }
        return coupons.get(randIdx).getId();
    }
}
