package Facade.LoginManager;

import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;

/**
 * Class used to manage user logins into system
 */
public class LoginManager {
    private static LoginManager instance = null;
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
    }


    /**
     * @param email The email for login.
     * @param password The password for login.
     * @param clientType The client type - used to select which type of Facade to attempt login with
     * @return The relevant client facade (based on chosen client type) if succeeded, null if failed
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public ClientFacade Login(String email, String password, ClientType clientType) throws CouponSystemException {
        ClientFacade clientFacade;
        // Part 1 - Initialize client facade based on client type (initial ID used will be updated when running 'CheckLogin' method)
        switch (clientType) {
            case Company -> clientFacade = new CompanyFacade(-1);
            case Customer -> clientFacade = new CustomerFacade(-1);
            case Administrator -> clientFacade = new AdminFacade();
            default -> clientFacade = null;
        };
        // Part 2 - Check login details
        if( CheckLogin(email,password, clientFacade) ) {
            return clientFacade;
        }
        else {
            throw new CouponSystemException(Errors.INCORRECT_LOGIN_DETAILS);
        }

    }


    /**
     * @param email The email for login.
     * @param password The password for login.
     * @param clientFacade The selected client facade type for the login.
     * @return True if login succeeded, false if login failed.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private static boolean CheckLogin(String email, String password, ClientFacade clientFacade) throws CouponSystemException {
        return clientFacade.Login(email, password);
    }
}
