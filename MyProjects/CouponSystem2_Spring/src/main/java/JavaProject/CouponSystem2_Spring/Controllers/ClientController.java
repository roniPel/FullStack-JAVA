package JavaProject.CouponSystem2_Spring.Controllers;


/**
 * Controller for All User types
 */
//Todo - which annotations to use? @RestController?
public abstract class ClientController {
    //Todo - write 'login' method - part 3
    abstract boolean login(String email, String password);
}
