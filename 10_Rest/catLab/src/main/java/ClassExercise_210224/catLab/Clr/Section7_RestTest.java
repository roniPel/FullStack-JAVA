package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Beans.Toy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@Order(4)
@RequiredArgsConstructor
public class Section7_RestTest implements CommandLineRunner {
    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        PrintSectionHeader();
        Rest_AddCat();
        Rest_UpdateCat();
        Rest_GetAllCats();
        Rest_GetOneCat();
        Rest_GetCatsByNameWeight();
        Rest_DeleteCat();
    }

    private void Rest_UpdateCat() {
        System.out.println("*** Method: Update Cat ***");
        //Todo - finish
    }

    private void Rest_AddCat() {
        System.out.println("*** Method: Add Cat ***");
        Cat cat1 = Cat.builder()
                .name("Rest Cat1 Add")
                .weight(5.0f)
                .toy(new Toy("String with fish"))
                .build();

        Cat cat2 = Cat.builder()
                .name("Rest Cat2 Add")
                .weight(3.0f)
                .toy(new Toy("Ball"))
                .build();

        try{
            ResponseEntity<String> responsePost = restTemplate.postForEntity
                    ("http://localhost:8080/api/catLab",cat1,String.class);
            System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?
                    "Cat was created":"Cat was NOT created");
        }catch (Exception err){
            System.out.println(err.getMessage());
        }
        System.out.println();
    }

    private void Rest_GetAllCats() {
        System.out.println("*** Method: Get All Cats ***");
        try {
            Cat[] cats = restTemplate.getForObject("http://localhost:8080/api/catLab", Cat[].class);
            List<Cat> catsList = Arrays.stream(cats).toList();
            catsList.forEach(System.out::println);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************          Section: REST API          ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
