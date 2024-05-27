package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Services.LoginService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.security.auth.login.LoginException;
import java.security.SignatureException;

/**
 * Controller for All User types - to help manage logins - ?
 */
@RequiredArgsConstructor
@CrossOrigin()
@Validated
@RestController
@RequestMapping("/Users")
public class LoginController extends ClientController{
    private final JWT jwt;
    private final LoginService loginService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean registerUser(@RequestBody Credentials userCredentials) throws Exception {
        loginService.registerUser(userCredentials);
        return true;
    }

    @PostMapping("/Login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Override
    String Login(@RequestBody Credentials userCredentials) throws LoginException {
        return loginService.Login(userCredentials.getUserName(),userCredentials.getUserPassword());
    }

    private boolean CheckToken(String token, ClientType clientType) throws LoginException, SignatureException {
        return jwt.checkUser(token,clientType);
    }
}
