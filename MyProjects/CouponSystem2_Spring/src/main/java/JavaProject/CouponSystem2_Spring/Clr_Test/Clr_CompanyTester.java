package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods.CompanyTestMethods_Rest;
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
    private CompanyTestMethods_Rest companyTestMethods_rest;
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
                // Run all methods - Services
                Company_RunAllMethods_Services(companyService);
                // Run all methods - Rest
                Company_RunAllMethods_Rest();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void Company_RunAllMethods_Rest() {
        PrintSectionHeader_Rest();
        companyTestMethods_rest.GetCompanyDetails();
        companyTestMethods_rest.AddCoupon();
        companyTestMethods_rest.UpdateCoupon();
        companyTestMethods_rest.DeleteCoupon();
        companyTestMethods_rest.GetCompanyCoupons();
        companyTestMethods_rest.GetCompanyCouponsByCategory();
        companyTestMethods_rest.GetCompanyCouponsByMaxPrice();
        PrintSectionFooter_Rest();
    }

    private void PrintSectionFooter_Rest() {
        //System.out.println();
        System.out.println("*************     End Company Methods (via Rest)     **************");
        System.out.println();
    }

    private void PrintSectionHeader_Rest() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************       Company Methods (via Rest)      **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    /**
     * Runs all Company user methods
     * @param companyService Service used to run the methods
     */
    private void Company_RunAllMethods_Services(CompanyService companyService) throws CompanyException {
        PrintSectionHeader_Services();
        companyTestMethods_services.GetCompanyDetails(companyService);
        companyTestMethods_services.AddCoupon(companyService);
        companyTestMethods_services.UpdateCoupon(companyService);
        companyTestMethods_services.DeleteCoupon(companyService);
        companyTestMethods_services.GetCompanyCoupons(companyService);
        companyTestMethods_services.GetCompanyCouponsByCategory(companyService);
        companyTestMethods_services.GetCompanyCouponsByMaxPrice(companyService);
        PrintSectionFooter_Services();
    }

    private void PrintSectionFooter_Services() {
        //System.out.println();
        System.out.println("*************   End Company Methods (via Services)   **************");
        System.out.println();
    }

    private void PrintSectionHeader_Services() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************     Company Methods (via Services)    **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
