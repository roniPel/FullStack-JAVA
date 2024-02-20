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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int a_id;
    @Column(name = "first_name")
    private String b_firstName;
    @Column(name = "last_name")
    private String c_lastName;
    @Column(unique = true,nullable = false,name = "email")
    private String d_email;
    @Column(length=10,name = "password",nullable = false)
    private String e_password;
    @Singular
    @ManyToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JoinTable(name = "customers_vs_coupons")
    private List<Coupon> coupons;
}
