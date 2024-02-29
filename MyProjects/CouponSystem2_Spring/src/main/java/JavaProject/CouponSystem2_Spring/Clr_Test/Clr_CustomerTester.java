package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.CustomerMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(4)
public class Clr_CustomerTester implements CommandLineRunner {
    @Autowired
    private LogonUtil logonUtil;
    @Autowired
    private CustomerMethods_Services customerMethods_services;

    @Autowired
    private CustomerService customerService; //- Preparation for Client Side (section 3)
    public boolean isLoggedOn = false;
    @Override
    public void run(String... args) throws Exception {
        String email = logonUtil.getEmailsPassowrdsMap().get("customerEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("customerPassword");

        try {
            //Todo - Remove check logon section and logon details
            // Check logon
            //CustomerService customerService = (CustomerService) LoginManager.Login(email, password, ClientType.Customer);

            // Run all methods
            Customer_RunAllMethods(customerService);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs all Customer user methods
     * @param customerService Service used to run the methods
     */
    private void Customer_RunAllMethods(CustomerService customerService) throws CustomerException {
        PrintSectionHeader();
        customerMethods_services.GetCustomerDetails(customerService);
        customerMethods_services.PurchaseCoupon(customerService);
        customerMethods_services.GetCustomerCoupons(customerService);
        customerMethods_services.GetCustomerCouponsByCategory(customerService);
        customerMethods_services.GetCustomerCouponsByMaxPrice(customerService);
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
