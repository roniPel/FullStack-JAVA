package JavaProject.CouponSystem2_Spring.Controllers;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.LoginException;
import java.security.SignatureException;

/**
 * Controller for All User types
 */
//Todo - which annotations to use? @RestController? (part 3)
@RequiredArgsConstructor
public class ClientController {
    private final JWT jwtUtil;
    private HttpHeaders getHeaders(String jwt) throws SignatureException {
        HttpHeaders headers = new HttpHeaders();
        String userJWT = jwt.split(" ")[1];
        if (jwtUtil.validateToken(userJWT)){
            headers.set("Authorization", "Bearer "+jwtUtil.generateToken(userJWT));
        }
        return headers;
    };
}
