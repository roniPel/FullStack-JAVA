package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.AdminMethods;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Clr_RunAllAdminMethods implements CommandLineRunner {
    @Autowired
    private LogonUtil logonUtil;
    @Autowired
    private AdminMethods adminMethods;
    public boolean isLoggedOn = false;

    @Override
    public void run(String... args) throws Exception {
        String email = logonUtil.getEmailsPassowrdsMap().get("adminEmail");
        String password = logonUtil.getEmailsPassowrdsMap().get("adminPassword");

        // Check logon
        AdminService adminService = (AdminService) LoginManager.Login(email,password,ClientType.Administrator);

        // Run all Admin methods
        Admin_RunAllMethods(adminService);

        // Prepare data for company and customer logons
        logonUtil.PrepareData_CustomerCompanyLogons();
    }

    /**
     * Runs all Admin user methods
     * @param adminService Service used to run the methods
     */
    private void Admin_RunAllMethods(AdminService adminService) throws AdminException {
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
    }
}
