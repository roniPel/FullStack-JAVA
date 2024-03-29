package JavaProject.CouponSystem2_Spring.Services;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginService implements ClientService{
    //Todo - copy from catbackend

    private final JWT jwt;
    //Repository to check user name in DB
    public String Login(String userName, String userPass) throws LoginException {
        Credentials credentials = new Credentials(1,userName,userPass,"ronir@email.com", ClientType.Administrator);
        if(userName.equals("admin") && userPass.equals("12345678")){
            return jwt.generateToken(credentials);
        }
        else {
            throw new LoginException("Incorrect User");
        }
    }

}
