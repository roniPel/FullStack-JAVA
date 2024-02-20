package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private int a_id;
    @Column(unique = true,nullable = false,updatable = false,name = "name")
    private String b_name;
    @Column(unique = true,nullable = false,name = "email")
    private String c_email;
    @Column(length=10,name = "password")
    private String d_password;
    @Singular
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "a_id",cascade = CascadeType.ALL)
    private List<Coupon> coupons;
}
