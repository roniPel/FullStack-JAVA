package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

/**
 * Controller for All User types - to help manage logins - ?
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/Users")
public class LoginController extends ClientController{

    private final LoginService loginService;
    @PostMapping("/Login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Override
    String Login(@RequestBody Credentials userCredentials) throws LoginException {
        return loginService.Login(userCredentials.getUserName(),userCredentials.getUserPass());
    }
}
