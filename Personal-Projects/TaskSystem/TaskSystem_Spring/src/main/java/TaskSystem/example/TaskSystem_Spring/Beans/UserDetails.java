package TaskSystem.example.TaskSystem_Spring.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.validator.constraints.Length;

@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    @Length(max = 40)
    private String name;

    @Column(length = 40)
    @Length(max = 40)
    private String password;

    @Column(length = 40)
    @Length(max = 40)
    private String email;

    private UserType userType;

    public UserDetails(int id, String email, String password, UserType userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public UserDetails(String email, String password, UserType userType){
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

}
