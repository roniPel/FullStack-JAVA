package ClassExercise_210224.catLab.Services;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Exceptions.CatsException;

import java.util.List;

public interface CatService {
    void AddCat(Cat cat) throws CatsException;

    void UpdateCat(Cat cat) throws CatsException;

    void DeleteCatById(int id) throws CatsException;
    List<Cat> GetAllCats();
    Cat GetOneCatById(int id) throws CatsException;
    List<Cat> GetCatsByNameAndWeight(String name, Float weight) throws CatsException;
}
