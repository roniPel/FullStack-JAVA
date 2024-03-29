package JavaProject.CouponSystem2_Spring.Utils;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT {
    //Todo - copy from catbackend
    //Type of encryption
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();

    //Secret Key
    //Todo - user environment variable
//    @Autowired
//    @Value("${jwt.secretKey}")
    private String secretKey = "aint+no+mountain+high+enough+aint+no+valley+low+enough";
    private Key decodeSecretKey = new SecretKeySpec(
            Base64.getDecoder().decode(secretKey), this.signatureAlgorithm
    );

    //Generate key
    public String generateToken(Credentials credentials) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", credentials.getUserEmail());
        claims.put("id", credentials.getId());
        claims.put("userType", credentials.getClientType());
        return "Bearer "+createToken(claims,credentials.getUserName());
    }

    //Create the JWT token
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

    private Claims extractAllClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodeSecretKey).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private String extractSignature(String token){
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodeSecretKey).build();
        return jwtParser.parseClaimsJws(token).getSignature();
    }

    public String extractSubject(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodeSecretKey).build();
        return extractAllClaims(token.replace("Bearer ","")).getSubject();
    }

    public String checkUser(String token) {
        Claims claims = extractAllClaims(token.replace("Bearer ",""));
        Credentials credentials = new Credentials();
        credentials.setUserName(claims.getSubject());
        credentials.setUserEmail((String)claims.get("userEmail"));
        credentials.setId((int)claims.get("id"));
        return generateToken(credentials);
    }
}
