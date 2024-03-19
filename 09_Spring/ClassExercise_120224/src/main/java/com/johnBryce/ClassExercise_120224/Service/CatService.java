package com.johnBryce.ClassExercise_120224.Service;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import com.johnBryce.ClassExercise_120224.Exceptions.CatsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {
    void AddCat(Cat cat) throws CatsException;
    void UpdateCat(int catId, Cat cat) throws CatsException;
    void DeleteCatById(int catId) throws CatsException;
    List<Cat> FindAllCats();
    Cat GetSingleCat(int catId) throws CatsException;
    List<Cat> FindByNameAndWeight() throws CatsException;
}
