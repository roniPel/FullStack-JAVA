package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

/**
 * Bean: Customer - used to define a customer listed in the system
 */
@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name",
            length = 20)
    @Length(max = 20)
    private String firstName;

    @Column(name = "last_name",
            length = 20)
    @Length(max = 20)
    private String lastName;

    @Column(unique = true,
            nullable = false,
            name = "email",
            length = 30)
    @Length(max = 30)
    @NotBlank
    private String email;

    @Column(length=15,
            name = "password",
            nullable = false)
    @Length(min = 5,
            max = 15)
    @NotBlank
    private String password;

    @Singular
    @ManyToMany(targetEntity = Coupon.class,
            cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "coupon_id")
    @JoinTable(name = "customers_vs_coupons")
    private List<Coupon> coupons;
}
