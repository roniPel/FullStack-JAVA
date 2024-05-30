package JavaProject.CouponSystem2_Spring.Beans;

import JavaProject.CouponSystem2_Spring.Login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    public String email;
    public String password;
    public ClientType userType;
}
