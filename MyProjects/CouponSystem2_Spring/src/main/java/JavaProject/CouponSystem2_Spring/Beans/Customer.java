package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    //Todo - add annotations to all beans - Client side, server side

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true,nullable = false,name = "email")
    private String email;

    @Column(length=10,name = "password",nullable = false)
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JoinTable(name = "customers_vs_coupons")
    private List<Coupon> coupons;
}
