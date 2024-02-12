package com.johnBryce.ClassExercise_120224.Repositories;

import com.johnBryce.ClassExercise_120224.Beans.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy,Integer> {
}
