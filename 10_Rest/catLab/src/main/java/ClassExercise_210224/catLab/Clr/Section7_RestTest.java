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
        Rest_GetAllCats();
        Rest_GetOneCat();
        Rest_GetCatsByNameWeight();
        Rest_DeleteCat();
        Rest_UpdateCat();
        PrintSectionFooter();
    }

    private void PrintSectionFooter() {
        System.out.println();
        System.out.println("**********************     End: REST API     **********************");
        System.out.println();
    }

    private void Rest_UpdateCat() {
        System.out.println("*** Method: Update Cat ***");
        int id = 11;
        // Part 1 - Get
        Cat updateCat = restTemplate.getForObject("http://localhost:8080/api/catLab/"+id,Cat.class);

        // Part 2 - Update
        updateCat.setName("Updated by Rest "+updateCat.getName());
        updateCat.setWeight(5.86f);

        // Part 3 - Put
        restTemplate.put
                ("http://localhost:8080/api/catLab/"+updateCat.getId(),updateCat);
        System.out.println("Cat "+updateCat.getId()+" was updated");
        System.out.println();
    }

    private void Rest_GetCatsByNameWeight() {
        System.out.println("*** Method: Get Cats by Name and Weight ***");
        String name = "Johnny";
        float weight = 3.0f;
        try {
            Cat[] cats = restTemplate.getForObject
                    ("http://localhost:8080/api/catLab/"+name+"/"+weight, Cat[].class);
            List<Cat> catsList = Arrays.stream(cats).toList();
            catsList.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Rest_DeleteCat() {
        System.out.println("*** Method: Delete Cat ***");
        int id = 27;
        try{
            restTemplate.delete("http://localhost:8080/api/catLab/"+id);
            System.out.println("Cat with id "+id+" was deleted.");
        } catch(Exception err){
            System.out.println(err.getMessage());
        }
        System.out.println();
    }

    private void Rest_GetOneCat() {
        System.out.println("*** Method: Get One Cat ***");
        int id = 10;
        try{
            Cat response = restTemplate.getForObject("http://localhost:8080/api/catLab/"+id,Cat.class);
            System.out.println(response);
        } catch (Exception err){
            System.out.println(err.getMessage());
        }
        System.out.println();
    }

    private void Rest_AddCat() {
        System.out.println("*** Method: Add Cat ***");
        Cat cat1 = Cat.builder()
                .name("Rest Cat3 Add")
                .weight(5.0f)
                .toy(new Toy("String with fish"))
                .build();

        Cat cat2 = Cat.builder()
                .name("Rest Cat4 Add")
                .weight(3.0f)
                .toy(new Toy("Ball"))
                .build();

        try{
            ResponseEntity<String> responsePost = restTemplate.postForEntity
                    ("http://localhost:8080/api/catLab",cat1,String.class);
            System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?
                    "Cat was created":"Cat was NOT created");
            ResponseEntity<String> responsePost2 = restTemplate.postForEntity
                    ("http://localhost:8080/api/catLab",cat2,String.class);
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
