package ClassExercise_210224.catLab.Repositories;

import ClassExercise_210224.catLab.Beans.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy,Integer> {
}
