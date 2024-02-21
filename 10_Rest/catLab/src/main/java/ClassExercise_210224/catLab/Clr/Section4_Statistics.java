package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

@Order(2)
public class Section4_Statistics implements CommandLineRunner {

    @Autowired
    private CatRepository catRepo;
    @Override
    public void run(String... args) throws Exception {

    }
}
