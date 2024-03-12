package ClassExercise_210224.catLab.Clr;

import ClassExercise_210224.catLab.Beans.Cat;
import ClassExercise_210224.catLab.Beans.Toy;
import ClassExercise_210224.catLab.Utils.CatUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CatUtilities catUtils;
    @Override
    public void run(String... args) {
        PrintSectionHeader();

        try {
            Rest_AddCat();
            Rest_GetAllCats();
            Rest_GetOneCat();
            Rest_DeleteCat();
            Rest_UpdateCat();
            FindByWeight_Asc();
            FindByWeight_Desc();
            FindByNameWeight_AND();
            FindByNameWeight_OR();
            FindAllStartWith();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintSectionFooter();
    }

    private void PrintSectionFooter() {
        System.out.println();
        System.out.println("**********************     End: REST API     **********************");
        System.out.println();
    }

    private void Rest_UpdateCat() {
        System.out.println("*** Method: Update Cat ***");
        int id = catUtils.GetRandomIdFromList(GetAllCats());
        // Part 1 - Get
        Cat updateCat = restTemplate.getForObject(
                "http://localhost:8080/api/catLab/GetSingleCat/"+id,Cat.class);

        // Part 2 - Update
        updateCat.setName("Updated by Rest "+updateCat.getName());
        updateCat.setWeight(5.86f);

        // Part 3 - Put
        restTemplate.put
                ("http://localhost:8080/api/catLab/UpdateCat/"+updateCat.getId(),updateCat);
        System.out.println("Cat "+updateCat.getId()+" was updated");
        System.out.println();
    }

    private void Rest_DeleteCat() {
        System.out.println("*** Method: Delete Cat ***");
        int id = catUtils.GetRandomIdFromList(GetAllCats());
        restTemplate.delete(
                "http://localhost:8080/api/catLab/DeleteCat/"+id);
        System.out.println("Cat with id "+id+" was deleted.");
        System.out.println();
    }

    private void Rest_GetOneCat() {
        System.out.println("*** Method: Get One Cat ***");
        int id = catUtils.GetRandomIdFromList(GetAllCats());
        Cat response = restTemplate.getForObject(
                "http://localhost:8080/api/catLab/GetSingleCat/"+id,Cat.class);
        System.out.println(response);
        System.out.println();
    }

    private void Rest_AddCat() {
        System.out.println("*** Method: Add Cat ***");
        // Create Cats
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

        // Save cats in DB (via Rest)
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/api/catLab/AddCat",cat1,String.class);
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?
                "Cat was created":"Cat was NOT created");
        ResponseEntity<String> responsePost2 = restTemplate.postForEntity
                ("http://localhost:8080/api/catLab/AddCat",cat2,String.class);
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?
                "Cat was created":"Cat was NOT created");
        System.out.println();
    }

    private void Rest_GetAllCats() {
        System.out.println("*** Method: Get All Cats ***");
        List<Cat> catsList = GetAllCats();
        catsList.forEach(System.out::println);
        System.out.println();
    }

    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************          Section: REST API          ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }

    private void FindAllStartWith() {
        System.out.println("*** Method: Get Cats Who's Name Starts With - ***");
        String name = "Jo";
        Cat[] cats = restTemplate.getForObject
                ("http://localhost:8080/api/catLab/GetCatsByNameStartingWith/"+name, Cat[].class);
        List<Cat> catsList = Arrays.stream(cats).toList();
        catsList.forEach(System.out::println);
        System.out.println();
    }

    private void FindByWeight_Desc() {
        System.out.println("*** Method: Get All Cats, Order By Weight - Desc ***");
        Cat[] cats = restTemplate.getForObject(
                "http://localhost:8080/api/catLab/GetAllCatsOrderByWeightDesc", Cat[].class);
        List<Cat> catsList = Arrays.stream(cats).toList();
        catsList.forEach(System.out::println);
        System.out.println();
    }

    private void FindByWeight_Asc() {
        System.out.println("*** Method: Get All Cats, Order By Weight - Asc ***");
        Cat[] cats = restTemplate.getForObject(
                "http://localhost:8080/api/catLab/GetAllCatsOrderByWeightAsc", Cat[].class);
        List<Cat> catsList = Arrays.stream(cats).toList();
        catsList.forEach(System.out::println);
        System.out.println();
    }

    private void FindByNameWeight_OR() {
        System.out.println("*** Method: Get Cats by Name or Weight ***");
        String name = "Johnny";
        float weight = 3.0f;
        Cat[] cats = restTemplate.getForObject
                ("http://localhost:8080/api/catLab/GetCatByNameOrWeight/"+name+"/"+weight, Cat[].class);
        List<Cat> catsList = Arrays.stream(cats).toList();
        catsList.forEach(System.out::println);
        System.out.println();
    }

    private void FindByNameWeight_AND() {
        System.out.println("*** Method: Get Cats by Name and Weight ***");
        String name = "Johnny";
        float weight = 3.0f;
        Cat[] cats = restTemplate.getForObject
                ("http://localhost:8080/api/catLab/GetCatByNameAndWeight/"+name+"/"+weight, Cat[].class);
        List<Cat> catsList = Arrays.stream(cats).toList();
        catsList.forEach(System.out::println);
        System.out.println();
    }

    private List<Cat> GetAllCats() {
        Cat[] cats = restTemplate.getForObject(
                "http://localhost:8080/api/catLab/GetAllCats", Cat[].class);
        return Arrays.stream(cats).toList();
    }
}
