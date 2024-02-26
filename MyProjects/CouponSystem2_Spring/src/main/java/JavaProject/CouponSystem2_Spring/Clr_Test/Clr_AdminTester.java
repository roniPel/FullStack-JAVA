package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.AdminMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class Clr_AdminTester implements CommandLineRunner {
    private LogonUtil logonUtil;
    private AdminMethods_Services adminMethods;

    // private AdminService adminService; - Preparation for Client Side (section 3)
    public boolean isLoggedOn = false;
    @Override
    public void run(String... args) {
        String email = logonUtil.getEmailsPassowrdsMap().get("adminEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("adminPassword");

        try {
            // Check logon
            AdminService adminService = (AdminService) LoginManager.Login(email, password, ClientType.Administrator);

            // Run all Admin methods - via services
            Admin_RunAllMethods_Services(adminService);

            //Todo - Add this section
            // Run all Admin methods - via RestTemplate
            //Admin_RunAllMethods_RestTemplate();

            // Prepare data for company and customer logons
            logonUtil.PrepareData_CustomerCompanyLogons();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs all Admin methods via Rest Template
     */
    private void Admin_RunAllMethods_RestTemplate() {
        PrintSectionHeader_RestTemplate();
        //Todo - insert Rest Methods
        PrintSectionFooter_RestTemplate();
    }
    private void PrintSectionFooter_RestTemplate() {
        System.out.println();
        System.out.println("***************   End Admin Methods (via Rest)   ***************");
        System.out.println();
    }
    private void PrintSectionHeader_RestTemplate() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************       Admin Methods (via Rest)      ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    /**
     * Runs all Admin user methods
     * @param adminService Service used to run the methods
     */
    private void Admin_RunAllMethods_Services(AdminService adminService) throws AdminException {
        PrintSectionHeader_Services();
        adminMethods.Method_GetAllCompanies(adminService);
        adminMethods.Method_GetAllCustomers(adminService);
        adminMethods.Method_AddCompany(adminService);
        adminMethods.Method_AddCustomer(adminService);
        adminMethods.Method_UpdateCompany(adminService);
        adminMethods.Method_UpdateCustomer(adminService);
        adminMethods.Method_GetOneCompany(adminService);
        adminMethods.Method_GetOneCustomer(adminService);
        adminMethods.Method_DeleteCompany(adminService);
        adminMethods.Method_DeleteCustomer(adminService);
        PrintSectionFooter_Services();
    }
    private void PrintSectionFooter_Services() {
        System.out.println();
        System.out.println("***************   End Admin Methods (via Services)   ***************");
        System.out.println();
    }
    private void PrintSectionHeader_Services() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************     Admin Methods (via Services)    ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
