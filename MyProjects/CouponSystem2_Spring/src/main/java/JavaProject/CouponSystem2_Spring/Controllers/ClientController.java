package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

/**
 * Controller for All User types
 */
//Todo - which annotations to use? @RestController? (part 3)
public abstract class ClientController {
    JWT jwt;
    abstract String Login(Credentials userCredentials) throws LoginException;
}
