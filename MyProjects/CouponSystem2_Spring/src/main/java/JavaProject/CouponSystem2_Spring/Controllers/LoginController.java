package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Services.LoginService.LoginServiceImpl;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.security.auth.login.LoginException;

/**
 * Controller for All User types - to help manage logins - ?
 */
@RequiredArgsConstructor
@CrossOrigin()
@Validated
@RestController
@RequestMapping("/Users")
public class LoginController{
    private final JWT jwt;
    private final LoginServiceImpl loginServiceImpl;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean registerUser(@RequestBody UserDetails userDetails) throws Exception {
        loginServiceImpl.registerUser(userDetails);
        return true;
    }

//    @PostMapping("/Login")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @Override
//    String Login(@RequestBody UserDetails userDetails) throws LoginException {
//        return loginService.Login(userDetails.getUserName(), userDetails.getUserPassword());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Credentials user) throws LoginException{
        HttpHeaders headers = new HttpHeaders();
        UserDetails userDetails = loginServiceImpl.Login(user);
        headers.set("Authorization","Bearer "+jwt.generateToken(userDetails));
        return new ResponseEntity<>(true,headers,HttpStatus.OK);
    }

}
