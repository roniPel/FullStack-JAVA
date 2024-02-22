package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Beans.Toy;
import ClassExercise_210224.catLab.Exceptions.CatsException;
import ClassExercise_210224.catLab.Services.CatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Order(3)
public class Section6_TestServices implements CommandLineRunner {

    @Autowired
    private CatServiceImpl catService;
    @Override
    public void run(String... args) throws Exception {
        Service_AddCat();
        Service_UpdateCat();
        Service_GetAllCats();
        Service_GetOneCat();
        Service_GetCatsByNameWeight();
        Service_DeleteCat();
    }

    private void Service_GetCatsByNameWeight() throws CatsException {
        System.out.println("*** Method: Get Cats By Name and Weight ***");
        String name = "Mitzi";
        Float weight = 5.0f;
        List<Cat> catsNameWeight = catService.GetCatsByNameAndWeight(name,weight);
        catsNameWeight.forEach(System.out::println);
        System.out.println();
    }

    private void Service_GetOneCat() throws CatsException {
        System.out.println("*** Method: Get One Cat ***");
        int id = 5;
        Cat getOneCatById = catService.GetOneCatById(id);
        System.out.println(getOneCatById);
        System.out.println();
    }

    private void Service_GetAllCats() {
        System.out.println("*** Method: Get All Cats ***");
        List<Cat> allCats = catService.GetAllCats();
        allCats.forEach(System.out::println);
        System.out.println();
    }

    private void Service_DeleteCat() throws CatsException {
        System.out.println("*** Method: Delete Cat ***");
        int id = 7;
        catService.DeleteCatById(id);
        System.out.println("Cat "+id+" was deleted");
        System.out.println();
    }

    private void Service_UpdateCat() throws CatsException {
        System.out.println("*** Method: Update Cat ***");
        int id = 1;
        Cat updateCat = catService.GetOneCatById(id);
        updateCat.setName("Updated "+updateCat.getName());
        updateCat.setWeight(8.8f);
        catService.UpdateCat(updateCat);
        System.out.println("Cat "+id+" was updated");
        System.out.println();
    }

    private void Service_AddCat() throws CatsException {
        System.out.println("*** Method: Add Cat ***");
        Cat cat1 = Cat.builder()
                .name("Mitzi")
                .weight(5.0f)
                .toy(new Toy("String with fish"))
                .build();
        catService.AddCat(cat1);
        System.out.println("Cat was added!");

        Cat cat2 = Cat.builder()
                .name("Johnny")
                .weight(3.0f)
                .toy(new Toy("Ball"))
                .build();
        catService.AddCat(cat2);
        System.out.println("Cat was added!");

        System.out.println();
    }
}
