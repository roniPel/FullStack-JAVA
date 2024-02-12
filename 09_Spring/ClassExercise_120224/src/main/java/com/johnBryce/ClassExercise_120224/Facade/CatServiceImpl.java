package com.johnBryce.ClassExercise_120224.Facade;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import com.johnBryce.ClassExercise_120224.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatServiceImpl implements CatService{

    @Autowired
    private CatRepository catRepo;

    @Override
    public boolean AddCat() {
        return false;
    }

    @Override
    public boolean UpdateCat() {
        return false;
    }

    @Override
    public boolean DeleteCatById(int id) {
        return false;
    }

    @Override
    public List<Cat> FindAllCats() {
        return null;
    }

    @Override
    public List<Cat> FindByNameAndWeight() {
        return null;
    }
}
