package com.Peled.Roni.config;

import com.Peled.Roni.beans.MyPrototype;
import com.Peled.Roni.beans.Student;
import com.Peled.Roni.beans.Teacher;
import com.Peled.Roni.beans.VintageClothing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {

    @Bean
    @Scope("prototype")
    public Student Roni() {
        Student student = new Student();
        student.setName("RoniRo");
        return student;
    }

    @Bean
    @Scope("singleton")
    public Teacher Zeev() {
        Teacher teacher = new Teacher();
        teacher.setName("Zeev");
        return teacher;
    }

    @Bean
    @Scope("prototype")
    public VintageClothing Shirt() {
        VintageClothing vintageClothing = new VintageClothing();
        vintageClothing.setId(111);
        vintageClothing.setName("Shirt "+vintageClothing.getId());
        return vintageClothing;
    }

    @Bean
    @Scope("prototype")
    public VintageClothing Pants() {
        VintageClothing vintageClothing = new VintageClothing();
        vintageClothing.setId(222);
        vintageClothing.setName("Pants "+vintageClothing.getId());
        return vintageClothing;
    }

}
