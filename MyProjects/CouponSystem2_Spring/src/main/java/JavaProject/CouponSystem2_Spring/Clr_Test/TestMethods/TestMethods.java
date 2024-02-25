package JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TestMethods {
    public double maxPrice = 200;
    public int GetrandInt(int range) {
        return (int)(Math.random()*(range));
    }

    public int GetRandIdFromList(List<?> myList) {
        int randIdx = (int)(Math.random()*(myList.size()));
        if(myList.size() == 1){
            randIdx = 0;
        }
        Object firstObject = myList.get(0);
        if(firstObject instanceof Customer) {
            return ((Customer) myList.get(randIdx)).getA_id();
        }
        else if(firstObject instanceof Company){
            return ((Company) myList.get(randIdx)).getA_id();
        }
        else if (firstObject instanceof Coupon) {
            return ((Coupon) myList.get(randIdx)).getA_id();
        }
        return -1;
    }
}
