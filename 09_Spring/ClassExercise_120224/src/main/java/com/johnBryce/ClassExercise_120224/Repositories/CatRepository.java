package com.johnBryce.ClassExercise_120224.Repositories;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}
