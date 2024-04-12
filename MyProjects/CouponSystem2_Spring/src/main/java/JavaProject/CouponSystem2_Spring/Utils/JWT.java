package JavaProject.CouponSystem2_Spring.Utils;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
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
    //Todo - user environment variable for secretKey variable (Part 3)
//    @Autowired
//    @Value("${jwt.secretKey}")
    private String secretKey = "aint+no+mountain+high+enough+aint+no+valley+low+enough";
    private Key decodeSecretKey = new SecretKeySpec(
            Base64.getDecoder().decode(secretKey), this.signatureAlgorithm
    );

    /**
     * Generates a token based on user credentials
     * @param credentials with user details in order to generate token
     * @return A token in String format
     */
    public String generateToken(Credentials credentials) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", credentials.getUserEmail());
        claims.put("id", credentials.getId());
        claims.put("userType", credentials.getClientType().name());
        return "Bearer "+createToken(claims,credentials.getUserName());
    }

    /**
     * Creates a JWT type token using the provided params
     * @param claims claims to insert into token body
     * @param userName user name to insert into token subject section
     * @return a token (in String format) signed with our secret key
     */
    private String createToken(Map<String, Object> claims, String userName) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(decodeSecretKey)
                .compact();
    }

    /**
     * Extracts all claims inside a provided token
     * @param token token in String format
     * @return The body of the token containing the 'claims' data
     */
    private Claims extractAllClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodeSecretKey).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * Extracts signature inside a provided token
     * @param token token in String format
     * @return Signature (in String format)
     */
    private String extractSignature(String token){
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodeSecretKey).build();
        return jwtParser.parseClaimsJws(token).getSignature();
    }

    /**
     * Extracts a subject from a provided token
     * @param token token in String format
     * @return Subject (in String format)
     */
    public String extractSubject(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodeSecretKey).build();
        return extractAllClaims(token.replace("Bearer ","")).getSubject();
    }

    /**
     * Extracts user details from provided token and returns a generated token
     * @param token token in String format
     * @return a token (in String format)
     */
    public String checkUser(String token) {
        Claims claims = extractAllClaims(token.replace("Bearer ",""));
        Credentials credentials = new Credentials();
        credentials.setUserName(claims.getSubject());
        credentials.setClientType((ClientType) claims.get("userType"));
        credentials.setUserEmail((String)claims.get("userEmail"));
        credentials.setId((int)claims.get("id"));
        return generateToken(credentials);
    }
}
