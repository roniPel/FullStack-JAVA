package JavaProject.CouponSystem2_Spring.Login;

import JavaProject.CouponSystem2_Spring.Services.ClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    public ClientService Login(String email, String password, ClientType clientType) {
        // Todo - finish writing method (singleton, compare to zeev's)
        return null;
    }
}
