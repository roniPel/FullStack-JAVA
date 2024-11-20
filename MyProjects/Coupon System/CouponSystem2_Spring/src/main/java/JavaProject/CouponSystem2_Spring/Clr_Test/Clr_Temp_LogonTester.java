package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Beans.ClientType;
import JavaProject.CouponSystem2_Spring.Utils.FillDbUtil;
import JavaProject.CouponSystem2_Spring.Services.LoginService.LoginServiceImpl;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.List;

//Todo - DELETE CLR
//@Component
@Order(6)
@RequiredArgsConstructor
public class Clr_Temp_LogonTester implements CommandLineRunner {
    private final LoginServiceImpl loginServiceImpl;
    private final FillDbUtil fillDbUtil;
    private final RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {
        try {
            //Add Credentials to DB
            String email = fillDbUtil.getEmailsPassowrdsMap().get("adminEmail");
            String password = fillDbUtil.getEmailsPassowrdsMap().get("adminPassword");
            ClientType clientType = ClientType.Administrator;

            Credentials credentials = new Credentials(email,password);

            PrintSectionHeader();
            System.out.println();

            // Creating a token - via JWT
            System.out.println("Email for login: "+email);
            System.out.println("User Details: ");
            UserDetails userDetails = loginServiceImpl.Login(credentials);
            System.out.println(userDetails);
            // Todo - Continue Test with RestController (get JWT token)

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
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail("credentials@example.com");
        userDetails.setName("JwtTest");
        userDetails.setClientType(ClientType.Company);
        fillDbUtil.AddCredentialsToDB(userDetails.getName(), userDetails.getPassword()
                , userDetails.getClientType(), userDetails.getEmail());

        String jwtToken = GenerateTokenForUser(userDetails);
        System.out.println("Generated token: \n" + jwtToken);
        ValidateTokenForRequest(jwtToken);
    }

    private String GenerateTokenForUser(UserDetails userDetails) {
        JWT jwtUtil = new JWT();
        return jwtUtil.generateToken(userDetails);
    }

    private void ValidateTokenForRequest(String tokenFromRequest) throws SignatureException {
        JWT jwtUtil = new JWT();
        String userEmail= jwtUtil.extractSubject(tokenFromRequest);
        System.out.println("Data in Subject: \n"+userEmail);
    }
}
