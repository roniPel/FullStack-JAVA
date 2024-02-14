package com.johnBryce.ClassExercise_120224.Service;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import com.johnBryce.ClassExercise_120224.ErrorManagement.CatsException;
import com.johnBryce.ClassExercise_120224.ErrorManagement.Errors;
import com.johnBryce.ClassExercise_120224.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService{
    @Autowired
    CatRepository catRepo;

    @Override
    public void AddCat(Cat cat) throws CatsException {
        int id = cat.getId();
        if(catRepo.existsById(id)) {
            throw new CatsException(Errors.ID_ALREADY_EXISTS);
        }
        catRepo.save(cat);
    }

    @Override
    public void UpdateCat(int catId, Cat cat) throws CatsException {
        if(!catRepo.existsById(cat.getId())){
            throw new CatsException(Errors.ID_NOT_FOUND);
        }
        catRepo.saveAndFlush(cat);
    }

    @Override
    public void DeleteCatById(int catId) throws CatsException {
        if(!catRepo.existsById(catId)){
            throw new CatsException(Errors.ID_NOT_FOUND);
        }
        catRepo.deleteById(catId);
    }

    @Override
    public List<Cat> FindAllCats() throws CatsException {
        return catRepo.findAll();
    }

    @Override
    public Cat GetSingleCat(int catId) throws CatsException {
        return catRepo.findById(catId).orElseThrow(()->new CatsException(Errors.ID_NOT_FOUND));
    }

    @Override
    public List<Cat> FindByNameAndWeight() throws CatsException {
        return null;
    }
}
