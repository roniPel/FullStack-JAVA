package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    //Todo - add annotations to all beans - Client side, server side
    // for client side errors (Length, etc.. see coupons2 in github)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(unique = true,
            nullable = false,
            updatable = false,
            name = "name")
    private String name;

    @Column(unique = true,
            nullable = false,
            name = "email")
    private String email;

    @Column(length=10,name = "password",
            nullable = false)

    //@Length(min = 10, max = 40)
    private String password;

    @Singular
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private Set<Coupon> coupons;
}
