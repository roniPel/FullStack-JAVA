package ClassExercise_210224.catLab.Repositories;

import ClassExercise_210224.catLab.Beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat,Integer> {
    List<Cat> findByNameAndWeight(String name, Float weight);
    List<Cat> findByNameOrWeight(String name, Float weight);
    List<Cat> findAllByOrderByWeightAsc();
    List<Cat> findAllByOrderByWeightDesc();
    List<Cat> findByNameStartingWith(String name);
}
