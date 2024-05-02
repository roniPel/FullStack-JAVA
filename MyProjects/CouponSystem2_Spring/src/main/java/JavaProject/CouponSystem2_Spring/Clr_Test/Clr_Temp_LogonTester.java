package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.LoginService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.security.SignatureException;

//Todo - DELETE CLR
@Component
@Order(6)
@RequiredArgsConstructor
public class Clr_Temp_LogonTester implements CommandLineRunner {
    private final LoginService loginService;
    private final LogonUtil logonUtil;
    @Override
    public void run(String... args) throws Exception {
        try {
            //Add Credentials to DB
            String password = "admin";
            String email = "admin@admin.com";
            loginService.AddCredentials(email, password, ClientType.Administrator, email);

            String token = loginService.Login(email, password);
            System.out.println("Logon Tester\n ================================\n");
            System.out.println(token);

            // Test JWT login
            Credentials credentials = new Credentials();
            credentials.setUserEmail("credentials@example.com");
            credentials.setUserName("JwtTest");
            credentials.setClientType(ClientType.Company);
            logonUtil.AddCredentialsToDB(credentials.getUserName(), credentials.getUserPass()
                    , credentials.getClientType(), credentials.getUserEmail());

            String jwtToken = GenerateTokenForUser(credentials);
            System.out.println("Generated token: \n" + jwtToken);
            ValidateTokenForRequest(jwtToken);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}

    private String GenerateTokenForUser(Credentials credentials) {
        JWT jwtUtil = new JWT();
        return jwtUtil.generateToken(credentials);
    }

    public void ValidateTokenForRequest(String tokenFromRequest) throws SignatureException {
        JWT jwtUtil = new JWT();
        String userEmail= jwtUtil.extractSubject(tokenFromRequest);
        System.out.println("Data in Subject: \n"+userEmail);
    }
}
