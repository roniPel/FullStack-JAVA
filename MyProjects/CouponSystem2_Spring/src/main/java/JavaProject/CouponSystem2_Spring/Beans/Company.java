package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

/**
 * Bean: Company - used to define a company listed in the system
 */
@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(unique = true,
            nullable = false,
            updatable = false,
            name = "name",
            length = 20)
    @NotBlank
    @Length(max = 20)
    private String name;

    @Column(unique = true,
            nullable = false,
            name = "email",
            length = 30)
    @Length(max = 30)
    @NotBlank
    private String email;

    @Column(length = 15,
            name = "password",
            nullable = false)
    @Length(min = 5,
            max = 15)
    private String password;


    @Singular
    @OneToMany(cascade = CascadeType.ALL,
//    @OneToMany(cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private List<Coupon> coupons;
}
