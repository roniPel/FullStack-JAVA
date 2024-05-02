package JavaProject.CouponSystem2_Spring.Login;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminService;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.security.auth.login.LoginException;

/**
 * LoginManager Class - used to manage and check user logins into the system
 */
//@Component
@RequiredArgsConstructor
public class LoginManager {
    // Todo - Delete LoginManager class? Or finish writing method + implement in code(?) - Part 3
    private static LogonUtil logonUtil;
    private static CompanyService companyService;
    private static CustomerService customerService;
    private static AdminService adminService;

    /**
     * @param email The email for login.
     * @param password The password for login.
     * @param clientType The client type - used to select which type of Facade to attempt login with
     * @return The relevant client service (based on chosen client type) if succeeded, null if failed
     * @throws AdminException If we get any exception.  Details are provided
     * @throws CompanyException If we get any exception.  Details are provided
     * @throws CustomerException If we get any exception.  Details are provided
     * @throws GuestException If we get any exception.  Details are provided
     */
    public static ClientService Login(String email, String password, ClientType clientType) throws AdminException, CompanyException, CustomerException, GuestException, LoginException {
        ClientService clientService;
        // Part 1 - Initialize client service based on client type (initial ID used will be updated when running 'CheckLogin' method)
        switch (clientType) {
            case Company -> clientService = companyService;
            case Customer -> clientService = customerService;
            case Administrator -> clientService = adminService;
            default -> clientService = null;
        }
        // Part 2 - Check login details
        if( CheckLogin(email, password, clientService) ) {
            // Print logon message
            logonUtil.Logon_Message(email,password, clientType);
            return clientService;
        }
        else {  // If login details are incorrect - throw exception based on requested client type
            switch (clientType) {
                case Company -> throw new CompanyException(CompanyErrors.INCORRECT_LOGIN_DETAILS);
                case Customer -> throw new CustomerException(CustomerErrors.INCORRECT_LOGIN_DETAILS);
                default -> throw new AdminException(AdminErrors.INCORRECT_LOGIN_DETAILS);
            }
        }
    }

    /**
     * @param email The email for login.
     * @param password The password for login.
     * @param clientService The selected client service type for the login.
     * @return True if login succeeded, false if login failed.
     * @throws AdminException,CompanyException,CustomerException If we get any exception.  Details are provided
     */
    private static boolean CheckLogin(String email, String password, ClientService clientService)
            throws AdminException, CompanyException, CustomerException, GuestException, LoginException {
        String token = clientService.Login(email, password);
        return true;

    }
}
