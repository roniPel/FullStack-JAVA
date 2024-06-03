package com.example.BackEnd_Spring.Repositories;

import com.example.BackEnd_Spring.Beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    List<Person> findByName(String name);
}
