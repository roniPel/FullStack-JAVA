package JavaProject.CouponSystem2_Spring.Login;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class LogonUtil {

    private Map<String, String> emailsPassowrdsMap;
    private boolean isLoggedOn = false;
    protected AdminService adminService;

    public LogonUtil() throws AdminException, CompanyException {
        PrepareData_AdminLogon();
    }

    public void PrepareData_CustomerCompanyLogons() throws AdminException, CompanyException {
        // Prepare data for company login
        String[] compDetails =adminService.AddCompanyDetailsForLogin();
        // Prepare data for customer login
        String[] custDetails =adminService.AddCustomerDetailsForLogin();

        // Add details to map:
        emailsPassowrdsMap.put("companyEmail",compDetails[0]);
        emailsPassowrdsMap.put("companyPassword",compDetails[1]);
        emailsPassowrdsMap.put("customerEmail",custDetails[0]);
        emailsPassowrdsMap.put("customerPassword",custDetails[1]);
    }
    private void PrepareData_AdminLogon() {
        emailsPassowrdsMap = new HashMap<>();
        emailsPassowrdsMap.put("adminEmail","admin@admin.com");
        emailsPassowrdsMap.put("adminPassword","admin");
    }

    /**
     * Tries to log into the system with provided params
     * If successful, runs all relevant methods
     * @param email email for logon
     * @param password password for logon
     * @param clientType client type requested for logon
     * @throws AdminException,CustomerException,CompanyException  If we get any exception.  Details are provided
     */
    void Logon_Message(String email, String password, ClientService clientType) throws AdminException, CustomerException, CompanyException {
        isLoggedOn = true;
        System.out.println("======================================");
        System.out.print(clientType + " is logged on. \n");
        System.out.println("======================================");
        System.out.println();
    }
}
