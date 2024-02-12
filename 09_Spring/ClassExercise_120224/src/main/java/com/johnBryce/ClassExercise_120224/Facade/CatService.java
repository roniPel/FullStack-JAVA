package com.johnBryce.ClassExercise_120224.Facade;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CatService {
    boolean AddCat();
    boolean UpdateCat();
    boolean DeleteCatById(int id);
    List<Cat> FindAllCats();
    List<Cat> FindByNameAndWeight();
}
