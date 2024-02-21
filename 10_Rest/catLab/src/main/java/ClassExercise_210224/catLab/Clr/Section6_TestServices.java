package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Beans.Toy;
import ClassExercise_210224.catLab.Exceptions.CatsException;
import ClassExercise_210224.catLab.Repositories.CatRepository;
import ClassExercise_210224.catLab.Services.CatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Section6_TestServices implements CommandLineRunner {

    @Autowired
    private CatServiceImpl catService;
    @Override
    public void run(String... args) throws Exception {
        Service_AddCat();
        Service_UpdateCat();
        Service_DeleteCat();
        Service_GetAllCats();
        Service_GetOneCat();
        Service_GetCatsByNameWeight();
    }

    private void Service_DeleteCat() throws CatsException {
        System.out.println("*** Method: Delete Cat ***");
        catService.DeleteCatById(3);
    }

    private void Service_UpdateCat() throws CatsException {
        System.out.println("*** Method: Update Cat ***");
        Cat updateCat = catService.GetOneCatById(2);
        updateCat.setName("Updated "+updateCat.getName());
        updateCat.setWeight(8.8f);
        catService.UpdateCat(updateCat);
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
    }
}
