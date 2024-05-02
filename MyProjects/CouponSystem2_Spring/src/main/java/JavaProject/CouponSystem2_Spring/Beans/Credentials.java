package JavaProject.CouponSystem2_Spring.Beans;

import JavaProject.CouponSystem2_Spring.Login.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    @Length(max = 40)
    private String userName;

    @Column(length = 40)
    @Length(max = 40)
    private String userPassword;

    @Column(length = 40)
    @Length(max = 40)
    private String userEmail;
    
    private ClientType clientType;

    public Credentials(int id, String email, String password, ClientType clientType) {
        this.id = id;
        this.userEmail = email;
        this.userPassword = password;
        this.clientType = clientType;
    }

    public Credentials(String email, String password, ClientType clientType){
        this.userEmail = email;
        this.userPassword = password;
        this.clientType = clientType;
    }
}
