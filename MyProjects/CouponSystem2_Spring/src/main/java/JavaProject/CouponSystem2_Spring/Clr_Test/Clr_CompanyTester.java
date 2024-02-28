package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.CompanyMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class Clr_CompanyTester implements CommandLineRunner {
    private LogonUtil logonUtil;
    private CompanyMethods_Services companyMethods_services;
    // private CompanyService companyService; - Preparation for Client Side (section 3)
    public boolean isLoggedOn = false;
    @Override
    public void run(String... args) throws Exception {
        String email = logonUtil.getEmailsPassowrdsMap().get("companyEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("companyPassword");

        try {
            // Check logon
            CompanyService companyService = (CompanyService) LoginManager.Login(email, password, ClientType.Company);

            // Run all methods
            Company_RunAllMethods(companyService);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void Company_RunAllMethods(CompanyService companyService) throws CompanyException {
        PrintSectionHeader();
        //Todo - call company methods
        companyMethods_services.GetCompanyDetails(companyService);
        companyMethods_services.AddCoupon(companyService);
        companyMethods_services.UpdateCoupon(companyService);
        companyMethods_services.DeleteCoupon(companyService);
        companyMethods_services.GetCompanyCoupons(companyService);
        companyMethods_services.GetCompanyCouponsByCategory(companyService);
        companyMethods_services.GetCompanyCouponsByMaxPrice(companyService);
        PrintSectionFooter();
    }

    private void PrintSectionFooter() {
        System.out.println();
        System.out.println("*************   End Company Methods (via Services)   **************");
        System.out.println();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************     Company Methods (via Services)    **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
