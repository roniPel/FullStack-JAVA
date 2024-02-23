package JavaProject.CouponSystem2_Spring.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
    private int a_id;
    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id",nullable = false,referencedColumnName = "id")
    private int b_company_id;
    //Todo - Ask Zeev: Ok to not have 'Category' table in DB?
    //private int companyId;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category_id")
    private Category c_category;
    @Column(name = "title",unique = true)
    private String d_title;
    @Column(name = "description")
    private String e_description;
    @Column(name = "start_date")
    private LocalDate f_startDate;
    @Column(name = "end_date")
    private LocalDate g_endDate;
    @Column(name = "amount")
    private int h_amount;
    @Column(scale = 2,name = "price")
    private double i_price;
    @Column(name = "image")
    private String j_image;
    @ManyToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Transient
    private List<Customer> customers;
}
