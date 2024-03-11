package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Bean: Coupon - used to define a coupon listed in the system
 */
@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_id",
            nullable = false)
    private Integer companyId;
    //Todo - Ask Zeev: Ok to not have 'Category' table in DB?

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category_id",
            nullable = false)
    private Category category;

    @Column(name = "title",
            length = 50,
            unique = true)
    @Length(max = 50)
    private String title;

    @Column(name = "description",
            length = 70)
    @Length(max = 70)
    private String description;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "amount")
    private int amount;

    @Column(scale = 2,
            name = "price")
    private double price;

    @Column(name = "image")
    private String image;

//    @ManyToMany(mappedBy = "coupons")
//    private List<Customer> customers;
}
