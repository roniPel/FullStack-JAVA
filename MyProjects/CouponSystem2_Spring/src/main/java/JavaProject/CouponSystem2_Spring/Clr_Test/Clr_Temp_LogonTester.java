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
//Todo - DELETE CLR
//@Component
@Order(6)
@RequiredArgsConstructor
public class Clr_Temp_LogonTester implements CommandLineRunner {
    private final LoginService loginService;
    private final LogonUtil logonUtil;
    @Override
    public void run(String... args) throws Exception {
        //Add Credentials to DB
        String user = "admin";
        String password = "admin@admin.com";
        String email = password;
        logonUtil.AddCredentialsToDB(user,password, ClientType.Administrator,email);

        String token = loginService.Login(user,password);
        System.out.println("Logon Tester\n ================================\n");
        System.out.println(token);

        // Test JWT login
        Credentials credentials = new Credentials();
        credentials.setUserEmail("credentials@example.com");
        credentials.setUserName("JwtTest");
        credentials.setId(123);
        credentials.setClientType(ClientType.Company);
        logonUtil.AddCredentialsToDB(credentials.getUserName(),credentials.getUserPass()
                ,credentials.getClientType(),credentials.getUserEmail());

        String jwtToken = GenerateTokenForUser(credentials);
        System.out.println("Generated token: \n"+jwtToken);
        ValidateTokenForRequest(jwtToken);
}

    private String GenerateTokenForUser(Credentials credentials) {
        JWT jwtUtil = new JWT();
        return jwtUtil.generateToken(credentials);
    }

    public void ValidateTokenForRequest(String tokenFromRequest) {
        JWT jwtUtil = new JWT();
        String userName= jwtUtil.extractSubject(tokenFromRequest);
        System.out.println("Data in Subject: \n"+userName);
    }
}
