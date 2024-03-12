package ClassExercise_210224.catLab.Controller;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Exceptions.CatsException;
import ClassExercise_210224.catLab.Services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/catLab")
public class CatController {
    @Autowired
    CatService catService;

    @GetMapping(value = {"/GetAllCats"})
    public List<Cat> getAllCats() {
        return catService.GetAllCats();
    }

    @GetMapping(value = "/GetCatByNameAndWeight/{name}/{weight}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cat> getCatsByNameAndWeight(@PathVariable String name, @PathVariable float weight) throws CatsException {
        return catService.GetCatsByNameAndWeight(name,weight);
    }

    @GetMapping(value = "/GetCatByNameOrWeight/{name}/{weight}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cat> getCatsByNameOrWeight(@PathVariable String name, @PathVariable float weight) throws CatsException {
        return catService.GetCatsByNameOrWeight(name,weight);
    }

    @GetMapping(value = "/GetAllCatsOrderByWeightAsc")
    @ResponseStatus(HttpStatus.OK)
    public List<Cat> GetAllCatsOrderByWeightAsc(){
        return catService.GetAllCatsOrderByWeightAsc();
    }

    @GetMapping(value = "/GetAllCatsOrderByWeightDesc")
    @ResponseStatus(HttpStatus.OK)
    public List<Cat> GetAllCatsOrderByWeightDesc(){
        return catService.GetAllCatsOrderByWeightDesc();
    }

    @GetMapping(value = "/GetCatsByNameStartingWith/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cat> GetCatsByNameStartingWith(@PathVariable String name){
        return catService.GetCatsByNameStartingWith(name);
    }

    @PostMapping(value = {"/AddCat"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addCat(@Validated @RequestBody Cat cat) throws CatsException {
        catService.AddCat(cat);
    }

    @PutMapping("/UpdateCat/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCat(@RequestBody Cat cat) throws CatsException {
        catService.UpdateCat(cat);
    }

    @DeleteMapping("/DeleteCat/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCat(@PathVariable int id) throws CatsException {
        catService.DeleteCatById(id);
    }

    @GetMapping("/GetSingleCat/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cat getSingleCat(@PathVariable int id) throws CatsException {
        return catService.GetOneCatById(id);
    }
}
