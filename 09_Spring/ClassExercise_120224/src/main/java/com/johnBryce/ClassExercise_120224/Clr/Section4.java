package com.johnBryce.ClassExercise_120224.Clr;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import com.johnBryce.ClassExercise_120224.Repositories.CatRepository;
import com.johnBryce.ClassExercise_120224.Repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class Section4 implements CommandLineRunner {

    @Autowired
    CatRepository catRepo;
    @Autowired
    ToyRepository toyRepo;
    @Override
    public void run(String... args) throws Exception {
        // Part 1 - Get all cats by weight and name
        System.out.println("All cats by weight and name: ");
        List<Cat> allCats = catRepo.findByNameAndWeight("Geula2", 4.8F);
        allCats.forEach(System.out::println);
        System.out.println();

        // Part 2 - Get all cats by weight or name
        System.out.println("All cats by weight or name: ");
        List<Cat> allCats2 = catRepo.findByNameOrWeight("Geula2", 4.8F);
        allCats2.forEach(System.out::println);
        System.out.println();

        // Part 3 - Get all cats by weight Desc
        String sortBy = "weight";
        String sortDir = "desc";
        Sort sort = setSort(sortBy,sortDir);

        System.out.println("All cats sorted by weight, Desc: ");
        List<Cat> allCats3 = catRepo.findAll(sort);
        allCats3.forEach(System.out::println);
        System.out.println();

        // Part 3 - Get all cats by weight Desc
        sortDir = "asc";
        System.out.println("All cats sorted by weight, Asc: ");
        sort = setSort(sortBy,sortDir);
        List<Cat> allCats4 = catRepo.findAll(sort);
        allCats4.forEach(System.out::println);
        System.out.println();
    }
    public Sort setSort(String sortBy, String sortDir){
        return sortDir.equalsIgnoreCase(Sort.Direction.ASC.name() )?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
    }
}
