package JavaProject.CouponSystem2_Spring.Clr_Test;


import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.AdminMethods_Services;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class Clr_AdminTester implements CommandLineRunner {
    @Autowired
    private LogonUtil logonUtil;
    @Autowired
    private AdminMethods_Services adminMethods_services;
    @Autowired
    private AdminService adminService; //  Preparation for Client Side (section 3)

    @Override
    public void run(String... args) {
        String email = logonUtil.getEmailsPassowrdsMap().get("adminEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("adminPassword");

        try {
            //Todo - Remove check logon section and logon details
            // Check logon
            //AdminService adminService = (AdminService) LoginManager.Login(email, password, ClientType.Administrator);

            // Run all Admin methods - via services
            Admin_RunAllMethods_Services(adminService);

            //Todo - Add this section
            // Run all Admin methods - via RestTemplate
            //Admin_RunAllMethods_RestTemplate();

            // Prepare data for company and customer logons
            int randomCompanyId = adminMethods_services.GetRandIdFromList(adminService.GetAllCompanies());
            logonUtil.PrepareData_CustomerCompanyLogons(randomCompanyId);

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
        //System.out.println();
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
        adminMethods_services.Method_GetAllCompanies(adminService);
        adminMethods_services.Method_GetAllCustomers(adminService);
        adminMethods_services.Method_AddCompany(adminService);
        adminMethods_services.Method_AddCustomer(adminService);
        adminMethods_services.Method_UpdateCompany(adminService);
        adminMethods_services.Method_UpdateCustomer(adminService);
        adminMethods_services.Method_GetOneCompany(adminService);
        adminMethods_services.Method_GetOneCustomer(adminService);
        adminMethods_services.Method_DeleteCompany(adminService);
        adminMethods_services.Method_DeleteCustomer(adminService);
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
