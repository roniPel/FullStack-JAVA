package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods.CompanyTestMethods_Rest;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.AdminTestMethods_Services;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.CompanyTestMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Clr Tester - used to test Company user methods
 */
@Component
@RequiredArgsConstructor
@Order(3)
public class Clr_CompanyTester implements CommandLineRunner {
    private final LogonUtil logonUtil;
    private final CompanyTestMethods_Services companyTestMethods_services;
    private final CompanyTestMethods_Rest companyTestMethods_rest;
    private final AdminTestMethods_Services adminTestMethods_services;
    private final AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Override
    public void run(String... args) throws Exception {

        try {
//
//            // Prepare data for company and customer testing and logons
//            int randomCompanyId = adminTestMethods_services.GetRandIdFromList(adminService.GetAllCompanies());
//            logonUtil.PrepareData_CustomerCompanyLogons(randomCompanyId);
//            //Todo - change logon format: remove 'SetCompanyId' method from Service (interface + impl class)
//            // + remove @Autowired and change to final -  'CompanyService' (local variable) - Part 3
//            companyService.SetCompanyId(randomCompanyId);
//
//            String email = logonUtil.getEmailsPassowrdsMap().get("companyEmail");
//            String password = logonUtil.getEmailsPassowrdsMap().get("companyPassword");

            // Check logon
            //Todo - Add Login Check with JWT (part 3)

            // Run all methods - Services (part 2)
            // Company_RunAllMethods_Services(companyService);

            // Run all methods - Rest
            //Todo - Uncomment section below (Part 3)
            Company_RunAllMethods_RestTemplate();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs all Company methods - via Rest Template
     */
    private void Company_RunAllMethods_RestTemplate() {
        PrintSectionHeader_RestTemplate();
        companyTestMethods_rest.GetCompanyDetails();
        companyTestMethods_rest.AddCoupon();
        companyTestMethods_rest.UpdateCoupon();
        companyTestMethods_rest.DeleteCoupon();
        companyTestMethods_rest.GetCompanyCoupons();
        companyTestMethods_rest.GetCompanyCouponsByCategory();
        companyTestMethods_rest.GetCompanyCouponsByMaxPrice();
        PrintSectionFooter_RestTemplate();
    }

    /**
     * Prints Footer for 'RestTemplate' Testing
     */
    private void PrintSectionFooter_RestTemplate() {
        //System.out.println();
        System.out.println("*************     End Company Methods (via Rest)     **************");
        System.out.println();
    }

    /**
     * Prints Header for 'RestTemplate' Testing
     */
    private void PrintSectionHeader_RestTemplate() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************       Company Methods (via Rest)      **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    /**
     * Runs all Company user methods - via services
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

    /**
     * Prints Footer for 'Services' Testing
     */
    private void PrintSectionFooter_Services() {
        //System.out.println();
        System.out.println("*************   End Company Methods (via Services)   **************");
        System.out.println();
    }

    /**
     * Prints Header for 'Services' Testing
     */
    private void PrintSectionHeader_Services() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************     Company Methods (via Services)    **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
