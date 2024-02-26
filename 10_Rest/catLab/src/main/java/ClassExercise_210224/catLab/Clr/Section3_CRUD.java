package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Beans.Toy;
import ClassExercise_210224.catLab.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Component
@Order(1)
public class Section3_CRUD implements CommandLineRunner {

    @Autowired
    private CatRepository catRepo;
    @Override
    public void run(String... args) throws Exception {
        PrintSectionHeader();
        CreateCats();
        UpdateCat();
        DeleteCatById();
        GetOneCatById();
        GetAllCats();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("*************          Section: Repositories          *************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    private void GetAllCats() {
        List<Cat> allCats = catRepo.findAll();
        System.out.println("All cats: ");
        allCats.forEach(System.out::println);
    }

    private void GetOneCatById() {
        int id = 3;
        Optional<Cat> getCat = catRepo.findById(id);
        System.out.println("Get one cat: ");
        getCat.ifPresent(System.out::println);
    }

    private void DeleteCatById() {
        int id = 3;
        Optional<Cat> deleteCat = catRepo.findById(id);
        deleteCat.ifPresent((cat)->{
            // Delete cat, but keep the toys
            cat.setToys(null);
            catRepo.deleteById(id);
        });
    }

    private void UpdateCat() {
        // Part 1 - get cat by ID
        Optional<Cat> updateCat = catRepo.findById(2);
        // Part 2 - print cat
        System.out.println("Cat before update: ");
        updateCat.ifPresent(System.out::println);

        updateCat.ifPresent((cat)->{
            // Part 3 - update cat
            cat.setName("Updated "+cat.getName());
            // Part 4 - save changes now
            catRepo.saveAndFlush(cat);
        });
    }

    public void CreateCats(){
        Cat cat1 = Cat.builder()
                .name("Mitzi")
                .weight(5.0f)
                .toy(new Toy("String with fish"))
                .build();

        Cat cat2 = Cat.builder()
                .name("Johnny")
                .weight(3.0f)
                .toy(new Toy("Ball"))
                .build();

        Cat cat3 = Cat.builder()
                .name("David")
                .weight(8.550f)
                .toy(new Toy("Scratch wall"))
                .build();

        catRepo.saveAll(Arrays.asList(cat1,cat2,cat3));
        System.out.println("Cats were created. ");
    }

}
