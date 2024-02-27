package JavaProject.CouponSystem2_Spring.Login;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.AdminService.AdminServiceImpl;
import JavaProject.CouponSystem2_Spring.Services.ClientService;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyServiceImpl;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    // Todo - finish writing method (singleton, compare to zeev's)
    //Todo - How to define?  Check if correct

    private static LogonUtil logonUtil;

    /*private static LoginManager instance = null;
    public LoginManager() {
    }

    public static LoginManager getInstance() {
        // Get instance with Double Check
        if (instance == null) {
            synchronized (LoginManager.class) {
                if(instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }*/

    /**
     * @param email The email for login.
     * @param password The password for login.
     * @param clientType The client type - used to select which type of Facade to attempt login with
     * @return The relevant client facade (based on chosen client type) if succeeded, null if failed
     * @throws AdminException,CompanyException,CustomerException If we get any exception.  Details are provided
     */
    public static ClientService Login(String email, String password, ClientType clientType) throws AdminException, CompanyException, CustomerException {
        ClientService clientService;
        // Part 1 - Initialize client service based on client type (initial ID used will be updated when running 'CheckLogin' method)
        switch (clientType) {
            case Company -> clientService = new CompanyServiceImpl(-1);
            case Customer -> clientService = new CustomerServiceImpl(-1);
            case Administrator -> clientService = new AdminServiceImpl();
            default -> clientService = null;
        }
        // Part 2 - Check login details
        if( CheckLogin(email, password, clientService) ) {
            // Print logon message
            logonUtil.Logon_Message(email,password, clientService);
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
    private static boolean CheckLogin(String email, String password, ClientService clientService) throws AdminException,CompanyException,CustomerException {
        return clientService.Login(email, password);
    }
}