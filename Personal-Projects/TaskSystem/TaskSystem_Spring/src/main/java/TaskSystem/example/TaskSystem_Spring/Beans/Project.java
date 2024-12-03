package TaskSystem.example.TaskSystem_Spring.Beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id",
            nullable = false)
    private Integer userId;

    @Column(name = "title",
            length = 10)
    @Length(max = 10)
    private String title;

    @Column(name = "details",
            length = 40)
    @Length(max = 40)
    private String details;

    @Singular
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Task> tasks;
}
