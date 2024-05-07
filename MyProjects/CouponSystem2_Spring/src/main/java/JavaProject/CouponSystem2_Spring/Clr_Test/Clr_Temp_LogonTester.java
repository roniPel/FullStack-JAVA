package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.LoginService;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;

//Todo - DELETE CLR
//@Component
@Order(6)
@RequiredArgsConstructor
public class Clr_Temp_LogonTester implements CommandLineRunner {
    private final LoginService loginService;
    private final LogonUtil logonUtil;
    private final RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {
        try {
            //Add Credentials to DB
            String email = logonUtil.getEmailsPassowrdsMap().get("adminEmail");
            String password = logonUtil.getEmailsPassowrdsMap().get("adminPassword");

            PrintSectionHeader();
            System.out.println();
//            TestJWTlogin();

            // Creating a token - via JWT
            System.out.println("Email for login: "+email);
            System.out.println("Token: ");
            String token = loginService.Login(email, password);
            System.out.println(token);
            // Get a list of companies with Authorization in Swagger
            GetListOfAllCompanies();

            PrintSectionFooter();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
    private void GetListOfAllCompanies() {
        System.out.println("*** Method: Get All Companies ***");
        Company[] companies = restTemplate.getForObject
                ("http://localhost:8080/Admin/GetAllCompanies_Authorization", Company[].class);
        List<Company> companyList = Arrays.stream(companies).toList();;
        companyList.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Prints Footer
     */
    private void PrintSectionFooter() {
        System.out.println();
        System.out.println("***************           End Logon Tester          ***************");
        System.out.println();
    }
    /**
     * Prints Header
     */
    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************            Logon Tester             ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    private void TestJWTlogin() throws SignatureException {
        Credentials credentials = new Credentials();
        credentials.setUserEmail("credentials@example.com");
        credentials.setUserName("JwtTest");
        credentials.setClientType(ClientType.Company);
        logonUtil.AddCredentialsToDB(credentials.getUserName(), credentials.getUserPassword()
                , credentials.getClientType(), credentials.getUserEmail());

        String jwtToken = GenerateTokenForUser(credentials);
        System.out.println("Generated token: \n" + jwtToken);
        ValidateTokenForRequest(jwtToken);
    }

    private String GenerateTokenForUser(Credentials credentials) {
        JWT jwtUtil = new JWT();
        return jwtUtil.generateToken(credentials);
    }

    private void ValidateTokenForRequest(String tokenFromRequest) throws SignatureException {
        JWT jwtUtil = new JWT();
        String userEmail= jwtUtil.extractSubject(tokenFromRequest);
        System.out.println("Data in Subject: \n"+userEmail);
    }
}
