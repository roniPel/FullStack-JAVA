package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.CompanyTestMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class Clr_CompanyTester implements CommandLineRunner {
    @Autowired
    private LogonUtil logonUtil;
    @Autowired
    private CompanyTestMethods_Services companyTestMethods_services;
    @Autowired
    private CompanyService companyService; // - Preparation for Client Side (section 3)

    @Override
    public void run(String... args) throws Exception {
        String email = logonUtil.getEmailsPassowrdsMap().get("companyEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("companyPassword");

        try {
            //Todo - Remove check logon section  and logon details
            //CompanyService companyService = (CompanyService) LoginManager.Login(email, password, ClientType.Company);

            // Check logon
            if(companyTestMethods_services.CheckLogin(email,password,companyService)) {
                // Run all methods
                Company_RunAllMethods(companyService);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs all Company user methods
     * @param companyService Service used to run the methods
     */
    private void Company_RunAllMethods(CompanyService companyService) throws CompanyException {
        PrintSectionHeader();
        companyTestMethods_services.GetCompanyDetails(companyService);
        companyTestMethods_services.AddCoupon(companyService);
        companyTestMethods_services.UpdateCoupon(companyService);
        companyTestMethods_services.DeleteCoupon(companyService);
        companyTestMethods_services.GetCompanyCoupons(companyService);
        companyTestMethods_services.GetCompanyCouponsByCategory(companyService);
        companyTestMethods_services.GetCompanyCouponsByMaxPrice(companyService);
        PrintSectionFooter();
    }

    private void PrintSectionFooter() {
        //System.out.println();
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
