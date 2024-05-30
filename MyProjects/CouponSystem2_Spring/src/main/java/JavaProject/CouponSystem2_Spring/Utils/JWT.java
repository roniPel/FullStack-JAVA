package JavaProject.CouponSystem2_Spring.Utils;

import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.LoginException;
import java.security.Key;
import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT class - used to check, generate, and manage security tokens for the system
 */
@Component
@NoArgsConstructor
public class JWT {
    //Type of encryption
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    //Todo - either delete 'GlobalVariables' config class or understand how to work
    // with global variables in spring (with application properties) or with @Value annotation
//    @Autowired
//    private GlobalVariables globalVariables;
    //@Value("${jwtTokenValidity}")
    private Integer getJwtTokenValidity = 30;  //Minutes
    //@Value("${jwtTokenEncodedKey}")
    private String getJwtTokenEncodedKey = "aint+no+mountain+high+enough+aint+no+valley+low+enough";
    private Key decodedSecretKey = new SecretKeySpec(
            Base64.getDecoder().decode(getJwtTokenEncodedKey), this.signatureAlgorithm
    );

    /**
     * Generates a token based on user credentials
     * @param userDetails with user details in order to generate token
     * @return A token in String format
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("clientType", userDetails.getClientType().toString());
        return createToken(claims, userDetails.getId());
    }
    public String generateToken(String token) throws SignatureException {
        Map<String, Object> claims = new HashMap<>();
        Claims ourClaims = extractAllClaims(token);
        claims.put("clientType", ourClaims.get("clientType"));
        return createToken(claims, Integer.parseInt(ourClaims.getSubject()));
    }

    /**
     * Creates a JWT type token using the provided params
     * @param claims claims to insert into token body
     * @param id user id to insert into token subject section
     * @return a token (in String format) signed with our secret key
     */
    private String createToken(Map<String, Object> claims, int id) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims) //get claims
                .setSubject(String.valueOf(id)) //get subject
                .setIssuedAt(Date.from(now)) //get current time
                .setExpiration(Date.from(now.plus(this.getJwtTokenValidity, ChronoUnit.MINUTES)))  //expiration date
                .signWith(this.decodedSecretKey)
                .compact();
    }

    /**
     * Extracts all claims inside a provided token
     * @param token token in String format
     * @return The body of the token containing the 'claims' data
     */
    private Claims extractAllClaims(String token) throws ExpiredJwtException,SignatureException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * Extracts signature inside a provided token
     * @param token token in String format
     * @return Signature (in String format)
     */
    //Todo - Delete method?
    private String extractSignature(String token){
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token).getSignature();
    }

    /**
     * Extracts a subject from a provided token
     * @param token token in String format
     * @return Subject (in String format)
     */
    //Todo - Delete method?
    public String extractSubject(String token) throws SignatureException {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return extractAllClaims(token.replace("Bearer ","")).getSubject();
    }
    public String extractEmail(String token) throws SignatureException {
        return extractAllClaims(token).getSubject();
    }
    public java.util.Date extractExpirationDate(String token) throws SignatureException {
        return extractAllClaims(token).getExpiration();
    }
    public boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException | SignatureException err) {
            return true;
        }
    }

    public String getUserType(String token) throws SignatureException {
        Claims claims = extractAllClaims(token);
        return (String) claims.get("clientType");
    }

    public boolean validateToken(String token) throws MalformedJwtException, SignatureException{
        final Claims claims = extractAllClaims(token);
        return true;
    }
    /**
     * Extracts user details from provided token and returns a generated token
     * @param token token in String format
     * @return a token (in String format)
     */
    public String checkUser(String token) throws SignatureException {
        Claims claims = extractAllClaims(token.replace("Bearer ",""));
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName((String)claims.get("userEmail"));
        userDetails.setClientType(ClientType.valueOf((String)claims.get("clientType") ) );
        userDetails.setUserEmail((String)claims.get("userEmail"));
        userDetails.setId(Integer.parseInt(claims.getSubject()));
        return generateToken(userDetails);
    }

    public boolean checkUser(String token, ClientType clientType) throws LoginException, SignatureException {
        String newToken = token.replace("Bearer ", "");
        if (validateToken(newToken)) {
            if (!getUserType(newToken).equals(clientType.name())) {
                throw new LoginException("User not allowed");
            }
        }
        return true;
    }
//    public static void main(String[] args) {
//        String email = "admin@admin.com";
//        String password = "admin";
//        ClientType type = ClientType.Administrator;
//        Credentials admin = new Credentials(email,password, type);
//        JWT jwt = new JWT();
//        String token = jwt.generateToken(admin);
//        System.out.println("TOKEN: "+token);
//        System.out.println("Check user: ");
//        try {
//            System.out.println(jwt.checkUser(token,type));
//        } catch (Exception e) {
//            System.out.println("ERROR! Exception: \n"+ e);
//        }
//    }
}
