package JavaProject.CouponSystem2_Spring.Beans;

import JavaProject.CouponSystem2_Spring.Login.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Credentials {
    //Todo - copy from catbackend
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    @Length(max = 40)
    private String userName;

    @Column(length = 40)
    @Length(max = 40)
    private String userPass;

    @Column(length = 40)
    @Length(max = 40)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;
}
