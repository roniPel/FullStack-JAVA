package com.johnBryce.ClassExercise_120224.clr;

import com.johnBryce.ClassExercise_120224.Beans.Cat;
import com.johnBryce.ClassExercise_120224.Beans.Toy;
import com.johnBryce.ClassExercise_120224.Repositories.CatRepository;
import com.johnBryce.ClassExercise_120224.Repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    CatRepository catRepo;
    @Autowired
    ToyRepository toyRepo;
    @Override
    public void run(String... args) throws Exception {
        // Create toys
        Toy t1 = new Toy("Ball");

        Toy t2 = new Toy("Feather on String");

        Toy t3 = new Toy("Mouse");

        Toy t4 = new Toy("Scratch wall");

        // Create Cats
        Cat c1 = Cat.builder()
                .name("Tzahi")
                .weight(3.52F)
                .toy(t1)
                .toy(t2)
                .build();

        Cat c2 = Cat.builder()
                .name("Geula")
                .weight(4.8F)
                .toys(Arrays.asList(t3,t2,t1))
                .build();

        Cat c3 = Cat.builder()
                .name("Billy")
                .weight(2.9F)
                .toy(t3)
                .build();

        Cat c4 = Cat.builder()
                .name("Joe")
                .weight(5.12F)
                .toy(t2)
                .toy(t4)
                .build();

        // Part 1 - Add cat
        catRepo.saveAll( Arrays.asList(c1,c2,c3,c4) );
        System.out.println("Cats were saved in DB. ");
        System.out.println();

        // Part 2 - Update cat
        c2.setName("Geula2");
        catRepo.save(c2);
        System.out.println("Cat "+c2.getName()+" was updated in DB. ");
        System.out.println();

        // Part 3 - Delete cat by ID
        catRepo.delete(c1);
        System.out.println("Cat "+c1.getName()+" was deleted. ");
        System.out.println();

        // Part 4 - Get single cat by ID
        System.out.println("Get cat (by ID): ");
        System.out.println(catRepo.findById(2));

        // Part 5 - Get all cats
        System.out.println(catRepo.findAll());
    }
}
