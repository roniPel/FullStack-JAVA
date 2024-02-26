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
        PrintSectionHeader();
        Service_AddCat();
        Service_UpdateCat();
        Service_GetAllCats();
        Service_GetOneCat();
        Service_GetCatsByNameWeight();
        Service_DeleteCat();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************          Section: Services          ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    private void Service_GetCatsByNameWeight()  {
        System.out.println("*** Method: Get Cats By Name and Weight ***");
        String name = "Mitzi";
        Float weight = 5.0f;
        try {
        List<Cat> catsNameWeight = catService.GetCatsByNameAndWeight(name,weight);
        catsNameWeight.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Service_GetOneCat() {
        System.out.println("*** Method: Get One Cat ***");
        int id = 5;
        try {
        Cat getOneCatById = catService.GetOneCatById(id);
        System.out.println(getOneCatById);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Service_GetAllCats() {
        System.out.println("*** Method: Get All Cats ***");
        List<Cat> allCats = catService.GetAllCats();
        allCats.forEach(System.out::println);
        System.out.println();
    }

    private void Service_DeleteCat() {
        System.out.println("*** Method: Delete Cat ***");
        int id = 7;
        try {
            catService.DeleteCatById(id);
            System.out.println("Cat " + id + " was deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Service_UpdateCat() {
        System.out.println("*** Method: Update Cat ***");
        int id = 1;
        try {
            Cat updateCat = catService.GetOneCatById(id);
            updateCat.setName("Updated " + updateCat.getName());
            updateCat.setWeight(8.8f);
            catService.UpdateCat(updateCat);
            System.out.println("Cat " + id + " was updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Service_AddCat() {
        System.out.println("*** Method: Add Cat ***");
        Cat cat1 = Cat.builder()
                .name("Service Cat1 Add")
                .weight(5.0f)
                .toy(new Toy("String with fish"))
                .build();

        Cat cat2 = Cat.builder()
                .name("Service Cat2 Add")
                .weight(3.0f)
                .toy(new Toy("Ball"))
                .build();

        try {
            catService.AddCat(cat1);
            System.out.println("Cat was added!");

            catService.AddCat(cat2);
            System.out.println("Cat was added!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
    }
}
