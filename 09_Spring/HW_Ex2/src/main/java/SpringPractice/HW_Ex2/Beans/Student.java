package SpringPractice.HW_Ex2.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int grade;
    private Date endDate;

}
