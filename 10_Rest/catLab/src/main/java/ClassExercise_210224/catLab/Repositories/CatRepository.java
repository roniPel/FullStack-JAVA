package ClassExercise_210224.catLab.Repositories;

import ClassExercise_210224.catLab.Beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat,Integer> {
}
