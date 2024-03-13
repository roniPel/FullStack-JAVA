package com.JohnBryce.Exam130324.Clr;

import com.JohnBryce.Exam130324.Beans.Grade;
import com.JohnBryce.Exam130324.Beans.Student;
import com.JohnBryce.Exam130324.Beans.Topic;
import com.JohnBryce.Exam130324.Repositories.StudentRepository;
import com.JohnBryce.Exam130324.Utilities.DateFactory;
import com.JohnBryce.Exam130324.Utilities.GradesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class FillDbWithMockData implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepo;
    @Value("${numberStudents}")
    private int numStudents;
    @Value("${numberGrades}")
    private int numGrades;
    @Autowired
    DateFactory dateFactory;
    @Autowired
    GradesFactory gradesFactory;

    @Override
    public void run(String... args) throws Exception {
        AddStudents(numStudents, numGrades);
    }

    private void AddStudents(int numStudents, int numGrades) {
        List<Student> students = new ArrayList<>();
        Long counter = 1L;
        for (int i = 1; i < numStudents+1; i++) {
            Long id = (long)i;
            String name = "Student"+i;
            Date birthday = dateFactory.GetRandomDate();

            // Create Grades
            List<Grade> grades = gradesFactory.CreateGradesList(counter,numGrades);
            counter += i*numGrades+1;

            //Create Student
            Student student = Student.builder()
                    .id(id)
                    .name(name)
                    .birthday(birthday)
                    .grades(grades)
                    .build();
            students.add(student);
        }
        studentRepo.saveAll(students);
    }
}
