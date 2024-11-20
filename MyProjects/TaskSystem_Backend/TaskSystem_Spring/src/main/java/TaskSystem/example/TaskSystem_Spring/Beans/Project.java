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

    @Column(name = "title",
            length = 10)
    @Length(max = 10)
    private String title;

    @Column(name = "details",
            length = 40)
    @Length(max = 40)
    private String details;

    @Singular
    @ManyToMany(targetEntity = Task.class)
    @PrimaryKeyJoinColumn(name = "task_id")
    @JoinTable(name = "tasks_vs_projects")
    private List<Task> tasks;
}
