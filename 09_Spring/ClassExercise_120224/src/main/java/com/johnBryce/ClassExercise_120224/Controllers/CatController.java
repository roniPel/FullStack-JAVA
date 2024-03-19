package com.johnBryce.ClassExercise_120224.Controllers;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import com.johnBryce.ClassExercise_120224.Exceptions.CatsException;
import com.johnBryce.ClassExercise_120224.Service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/catstoysdb")
public class CatController {

    @Autowired
    CatService catService;

    @GetMapping
    public List<Cat> getAllCats() {
        return catService.FindAllCats();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCat(@Validated @RequestBody Cat cat) throws CatsException {
        catService.AddCat(cat);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCat(@PathVariable int id, @RequestBody Cat cat) throws CatsException {
        catService.UpdateCat(id,cat);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCat(@PathVariable int id) throws CatsException {
        catService.DeleteCatById(id);
    }

}
