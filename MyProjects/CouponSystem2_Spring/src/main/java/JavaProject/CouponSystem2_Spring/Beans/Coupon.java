package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    //Todo - add annotations to all beans - Client side, server side

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    //@ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)

    @Column(name = "company_id",nullable = false)
    private Integer companyId;
    //Todo - Ask Zeev: Ok to not have 'Category' table in DB?
    //private int companyId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category_id",nullable = false)
    private Category category;

    @Column(name = "title",unique = true)
    private String title;

    @Column(name = "description")
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
}
