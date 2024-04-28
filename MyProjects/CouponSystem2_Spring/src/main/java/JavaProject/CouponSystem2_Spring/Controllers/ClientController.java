package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import javax.security.auth.login.LoginException;

/**
 * Controller for All User types
 */
//Todo - which annotations to use? @RestController? (part 3)
public abstract class ClientController {
    //Todo - write 'login' method - part 3
    abstract String Login(Credentials userCredentials) throws LoginException;
}
