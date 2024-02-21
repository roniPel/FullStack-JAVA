package ClassExercise_210224.catLab.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cats")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 40)
    private String name;
    private float weight;
    @Singular
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Toy> toys;
}
