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
    private Integer a_id;
    @Column(unique = true,
            nullable = false,
            updatable = false,
            name = "name")
    private String b_name;
    @Column(unique = true,
            nullable = false,
            name = "email")
    private String c_email;
    @Column(length=10,name = "password",
            nullable = false)
    private String d_password;
    @Singular
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private List<Coupon> coupons;

    /*public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
        coupon.setB_company_id(this.getA_id());
    }

    public void removeCoupon(Coupon coupon) {
        coupons.remove(coupon);
        coupon.setB_company_id(null);
    }*/
}
