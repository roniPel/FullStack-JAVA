package TaskSystem.example.TaskSystem_Spring.Beans;

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

    public UserDetails(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserDetails(String email, String password){
        this.email = email;
        this.password = password;
    }

}
