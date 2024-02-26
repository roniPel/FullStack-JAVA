package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.CustomerMethods_Services;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(4)
public class Clr_CustomerTester implements CommandLineRunner {
    private LogonUtil logonUtil;
    private CustomerMethods_Services customerMethods;

    // private CustomerService customerService; - Preparation for Client Side (section 3)
    public boolean isLoggedOn = false;
    @Override
    public void run(String... args) throws Exception {
        String email = logonUtil.getEmailsPassowrdsMap().get("customerEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("customerPassword");

        try {
            // Check logon
            CustomerService customerService = (CustomerService) LoginManager.Login(email, password, ClientType.Customer);

            // Run all methods
            Customer_RunAllMethods(customerService);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void Customer_RunAllMethods(CustomerService customerService) {
        PrintSectionHeader();
        //Todo - call company methods
        PrintSectionFooter();
    }

    private void PrintSectionFooter() {
        System.out.println();
        System.out.println("************   End Customer Methods (via Services)   **************");
        System.out.println();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("*************     Customer Methods (via Services)    **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
