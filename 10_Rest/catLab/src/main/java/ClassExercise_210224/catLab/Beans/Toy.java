package ClassExercise_210224.catLab.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "toys")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 40)
    private String name;

    public Toy(String name) {
        this.name = name;
    }
}
