package com.johnBryce.ClassExercise_120224.Repositories;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Integer> {

    // INCORRECT !!!
    //List<Cat> locateByNameAndWeight(String name, float weight);
    List<Cat> findByNameAndWeight(String name, float weight);
    List<Cat> findByNameOrWeight(String name, float weight);

}
