package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
public class Clr_FillDBwithMockData implements CommandLineRunner {
    //Todo - write methods (copy from MockData?):

    //Todo - how to write login manager - getInstance?
    public LoginManager loginManager = LoginManager.builder().build();
    public boolean isLoggedOn = false;
    private Map<String, String> emailsPassowrdsMap;
    private Map<String, Object> mockDataMap;
    @Override
    public void run(String... args) throws Exception {
        // Todo - Delete method? FillInCategoryTable();

        // FillInCompanyTable(numberOfCompanies);
        // CreateCouponsForAllCompanies(numberOfCouponsPerCompany,amountCouponsPerType,maxPrice);
        // FillInCustomerTable(numberOfCustomers);
        // FillInCustomerVsCouponsTable();
        // AddExpiredCoupon();
    }

    public void FillInCategoryTable() {
        String category;

        // Prepare params Map with Category values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < Category.values().length; i++) {
            category = String.valueOf(Category.values()[i]);
            params.put(counter,category);
            counter++;
        }

    }
}
