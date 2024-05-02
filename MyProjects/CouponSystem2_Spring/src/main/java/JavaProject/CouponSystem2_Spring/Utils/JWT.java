package JavaProject.CouponSystem2_Spring.Utils;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
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

    //Secret Key
    //Todo - use environment variable for secretKey variable (Part 3)
//    @Autowired
//    @Value("${jwt.secretKey}")
    private String encodedSecretKey = "aint+no+mountain+high+enough+aint+no+valley+low+enough";
    private Key decodedSecretKey = new SecretKeySpec(
            Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm
    );

    /**
     * Generates a token based on user credentials
     * @param credentials with user details in order to generate token
     * @return A token in String format
     */
    public String generateToken(Credentials credentials) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("clientType", credentials.getClientType().name());
        return createToken(claims, credentials.getUserEmail());
    }
    public String generateToken(String token) throws SignatureException {
        Map<String, Object> claims = new HashMap<>();
        Claims ourClaims = extractAllClaims(token);
        claims.put("clientType", ourClaims.get("clientType"));
        return createToken(claims, ourClaims.getSubject());
    }

    /**
     * Creates a JWT type token using the provided params
     * @param claims claims to insert into token body
     * @param email user email to insert into token subject section
     * @return a token (in String format) signed with our secret key
     */
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims) //get claims
                .setSubject(email) //get subject
                .setIssuedAt(Date.from(now)) //get current time
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))  //expiration date
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
        Credentials credentials = new Credentials();
        credentials.setUserName(claims.getSubject());
        credentials.setClientType((ClientType) claims.get("clientType"));
        credentials.setUserEmail((String)claims.get("userEmail"));
        credentials.setId((int)claims.get("id"));
        return generateToken(credentials);
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
    public static void main(String[] args) {
        Credentials zeev = new Credentials("admin@admin.com","admin", ClientType.Administrator);
        JWT jwt = new JWT();
        String token = jwt.generateToken(zeev);
        System.out.println("TOKEN: "+token);
    }
}
