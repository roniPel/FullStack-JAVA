package TaskSystem.example.TaskSystem_Spring.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    // with a title, description, due date, priority level, and project category.
    @Column(name = "title",
            length = 50,
            unique = true)
    @Length(max = 50)
    public String title;

    @Column(name = "description",
            length = 70)
    @Length(max = 70)
    public String description;
    @Column(name = "due_date")
    private LocalDate due_date;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority_id",
            nullable = false)
    public Integer priority;

    @Column(name = "project_id",
            nullable = false)
    private Project project;

}
