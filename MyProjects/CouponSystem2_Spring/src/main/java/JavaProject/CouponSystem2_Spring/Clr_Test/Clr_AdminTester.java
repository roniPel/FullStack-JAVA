package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods.AdminTestMethods_Rest;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.AdminTestMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Clr Tester - used to test Admin user methods
 */
@Component
@Order(2)
@RequiredArgsConstructor
public class Clr_AdminTester implements CommandLineRunner {
    private final LogonUtil logonUtil;
    private final AdminTestMethods_Services adminTestMethods_services;
    private final AdminTestMethods_Rest adminTestMethods_Rest;
    private final AdminService adminService; //  Preparation for Client Side (section 3)

    @Override
    public void run(String... args) {
        String email = logonUtil.getEmailsPassowrdsMap().get("adminEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("adminPassword");

        try {
            // Run all Admin methods - via services
            Admin_RunAllMethods_Services(adminService);

            Admin_RunAllMethods_RestTemplate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs all Admin methods - via Rest Template
     */
    private void Admin_RunAllMethods_RestTemplate() {
        PrintSectionHeader_RestTemplate();
        adminTestMethods_Rest.Method_GetAllCompanies();
        adminTestMethods_Rest.Method_GetAllCustomers();
        adminTestMethods_Rest.Method_AddCompany();
        adminTestMethods_Rest.Method_AddCustomer();
        adminTestMethods_Rest.Method_UpdateCompany();
        adminTestMethods_Rest.Method_UpdateCustomer();
        adminTestMethods_Rest.Method_GetOneCompany();
        adminTestMethods_Rest.Method_GetOneCustomer();
        adminTestMethods_Rest.Method_DeleteCompany();
        adminTestMethods_Rest.Method_DeleteCustomer();
        PrintSectionFooter_RestTemplate();
    }

    /**
     * Prints Footer for 'RestTemplate' Testing
     */
    private void PrintSectionFooter_RestTemplate() {
        //System.out.println();
        System.out.println("***************   End Admin Methods (via Rest)   ***************");
        System.out.println();
    }
    /**
     * Prints Header for 'RestTemplate' Testing
     */
    private void PrintSectionHeader_RestTemplate() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************       Admin Methods (via Rest)      ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    /**
     * Runs all Admin user methods - via services
     * @param adminService Service used to run the methods
     */
    private void Admin_RunAllMethods_Services(AdminService adminService) throws AdminException {
        PrintSectionHeader_Services();
        adminTestMethods_services.Method_GetAllCompanies(adminService);
        adminTestMethods_services.Method_GetAllCustomers(adminService);
        adminTestMethods_services.Method_AddCompany(adminService);
        adminTestMethods_services.Method_AddCustomer(adminService);
        adminTestMethods_services.Method_UpdateCompany(adminService);
        adminTestMethods_services.Method_UpdateCustomer(adminService);
        adminTestMethods_services.Method_GetOneCompany(adminService);
        adminTestMethods_services.Method_GetOneCustomer(adminService);
        adminTestMethods_services.Method_DeleteCompany(adminService);
        adminTestMethods_services.Method_DeleteCustomer(adminService);
        PrintSectionFooter_Services();
    }
    /**
     * Prints Footer for 'Services' Testing
     */
    private void PrintSectionFooter_Services() {
        System.out.println();
        System.out.println("***************   End Admin Methods (via Services)   ***************");
        System.out.println();
    }
    /**
     * Prints Header for 'Services' Testing
     */
    private void PrintSectionHeader_Services() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************     Admin Methods (via Services)    ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
