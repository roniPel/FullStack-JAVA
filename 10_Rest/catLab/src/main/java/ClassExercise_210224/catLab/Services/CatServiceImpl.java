package ClassExercise_210224.catLab.Services;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Exceptions.CatsException;
import ClassExercise_210224.catLab.Exceptions.Errors;
import ClassExercise_210224.catLab.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CatServiceImpl implements CatService{
    @Autowired
    private CatRepository catRepo;

    @Override
    public void AddCat(Cat cat) throws CatsException {
        int id = cat.getId();
        if(catRepo.existsById(id)){
            throw new CatsException(Errors.ID_ALREADY_EXISTS);
        }
        catRepo.save(cat);
    }

    @Override
    public void UpdateCat(Cat cat) throws CatsException {
        int id = cat.getId();
        if(!catRepo.existsById(id)){
            throw new CatsException(Errors.ID_NOT_FOUND);
        }
        catRepo.saveAndFlush(cat);
    }

    @Override
    public void DeleteCatById(int id) throws CatsException {
        if(!catRepo.existsById(id)){
            throw new CatsException(Errors.ID_NOT_FOUND);
        }
        catRepo.deleteById(id);
    }

    @Override
    public List<Cat> GetAllCats() {
        return catRepo.findAll();
    }

    @Override
    public Cat GetOneCatById(int id) throws CatsException {
        return catRepo.findById(id).orElseThrow( ()->new CatsException(Errors.ID_NOT_FOUND) );
    }

    @Override
    public List<Cat> GetCatsByNameAndWeight(String name, Float weight) throws CatsException {
        if(weight<0){
            throw new CatsException(Errors.WEIGHT_ERROR);
        }
        return catRepo.findByNameAndWeight(name,weight);
    }
}
