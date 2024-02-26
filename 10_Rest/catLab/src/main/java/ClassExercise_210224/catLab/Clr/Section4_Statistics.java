package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Order(2)
public class Section4_Statistics implements CommandLineRunner {
    @Autowired
    private CatRepository catRepo;
    @Override
    public void run(String... args) throws Exception {
        PrintSectionHeader();
        FindByNameWeight_AND();
        FindByNameWeight_OR();
        FindByWeight_Asc();
        FindByWeight_Desc();
        FindAllStartWith();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************          Section: Statistics          **************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    private void FindAllStartWith() {
        String name = "Jo";
        List<Cat> findByStartsWith = catRepo.findByNameStartingWith(name);
        System.out.println("Cats starting with: "+name);
        findByStartsWith.forEach(System.out::println);
    }

    private void FindByWeight_Desc() {
        List<Cat> findByWeightDesc = catRepo.findAllByOrderByWeightDesc();
        System.out.println("All cats order by Weight(Desc)");
        findByWeightDesc.forEach(System.out::println);
    }

    private void FindByWeight_Asc() {
        List<Cat> findByWeightAsc = catRepo.findAllByOrderByWeightAsc();
        System.out.println("All cats order by Weight(Asc)");
        findByWeightAsc.forEach(System.out::println);
    }

    private void FindByNameWeight_OR() {
        List<Cat> findByNameOrWeight = catRepo.findByNameOrWeight("David",3.0f);
        System.out.println("Find by name or weight: ");
        findByNameOrWeight.forEach(System.out::println);
    }

    private void FindByNameWeight_AND() {
        List<Cat> findByNameAndWeight = catRepo.findByNameAndWeight("David",3.0f);
        System.out.println("Find by name and weight: ");
        findByNameAndWeight.forEach(System.out::println);
    }
}
