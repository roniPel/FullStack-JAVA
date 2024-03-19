package com.JohnBryce.Exam130324.Clr;

import com.JohnBryce.Exam130324.Beans.Grade;
import com.JohnBryce.Exam130324.Beans.Student;
import com.JohnBryce.Exam130324.Utilities.DateFactory;
import com.JohnBryce.Exam130324.Utilities.GradesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class SchoolTesting implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GradesFactory gradesFactory;
    @Autowired
    DateFactory dateFactory;

    @Override
    public void run(String... args) {
        PrintSectionHeader();

        try {
            Method_GetAllStudents();
            Method_AddStudent();
            Method_GetOneStudent();
            Method_GetStudentByName();
            Method_GetStudentAverage();
            Method_DeleteStudent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintSectionFooter();
    }

    private void Method_DeleteStudent() {
        System.out.println("*** Method: Delete Student ***");
        List<Student> students = GetListOfAllStudents();
        // Select random ID for deletion
        Long idToDelete = gradesFactory.GetRandIdFromList(students);
        Student studentToDelete = restTemplate.getForObject
                ("http://localhost:8080/api/School/GetOneStudent/"+idToDelete,Student.class);
        System.out.println("Student to delete: \n"+studentToDelete);
        // Delete student
        restTemplate.delete("http://localhost:8080/api/School/DeleteStudent/"+idToDelete);
        System.out.println("Deleted Student? true");
        System.out.println();
    }
    private void Method_GetStudentAverage() {
        System.out.println("*** Method: Get Student Average ***");
        List<Student> students = GetListOfAllStudents();
        // Pick random Id from students
        Long getOneStudId = gradesFactory.GetRandIdFromList(students);
        Double average = restTemplate.getForObject
                ("http://localhost:8080/api/School/GetStudentAverage/"+getOneStudId,Double.class);
        // Print student
        System.out.println("Student id: "+getOneStudId);
        System.out.println("Student average: "+average);
        System.out.println();
    }
    private void Method_GetStudentByName() {
        System.out.println("*** Method: Get Student By Name ***");
        List<Student> students = GetListOfAllStudents();
        // Pick random Id from students
        Long getOneStudId = gradesFactory.GetRandIdFromList(students);
        String name = "Student"+getOneStudId;
        Student[] studentsByName = restTemplate.getForObject
                ("http://localhost:8080/api/School/GetStudentByName/"+name,Student[].class);
        List<Student> studentList = Arrays.stream(studentsByName).toList();

        // Print students
        System.out.println("Students by name "+name+": ");
        studentList.forEach(System.out::println);
        System.out.println();
    }
    private void Method_GetOneStudent() {
        System.out.println("*** Method: Get One Student ***");
        List<Student> students = GetListOfAllStudents();
        // Pick random Id from students
        Long getOneStudId = gradesFactory.GetRandIdFromList(students);
        Student student = restTemplate.getForObject
                ("http://localhost:8080/api/School/GetOneStudent/"+getOneStudId,Student.class);
        // Print student
        System.out.println("One Student: \n"+student);
        System.out.println();
    }
    private void Method_AddStudent() {
        System.out.println("*** Method: Add Student ***");
        // Create student
        Long id = 100L;
        String name = "AddStudent"+id;
        Date birthday = dateFactory.GetRandomDate();
        List<Grade> grades = gradesFactory.CreateGradesList(400L,3);;
        Student student = Student.builder()
                .id(id)
                .name(name)
                .birthday(birthday)
                .grades(grades)
                .build();

        System.out.println("Student to add: ");
        System.out.println(student);
        // Add customer to DB
        ResponseEntity<String> responsePost = restTemplate.postForEntity
                ("http://localhost:8080/api/School/AddStudent",student,String.class);
        System.out.print("Added Student? ");
        System.out.println(responsePost.getStatusCode().value()== HttpStatus.CREATED.value()?"true":"false");
        System.out.println();
    }
    private void Method_GetAllStudents() {
        System.out.println("*** Method: Get All Students ***");
        List<Student> students = GetListOfAllStudents();
        students.forEach(System.out::println);
        System.out.println();
    }
    private List<Student> GetListOfAllStudents() {
        Student[] students = restTemplate.getForObject
                ("http://localhost:8080/api/School/GetAllStudents", Student[].class);
        return Arrays.stream(students).toList();
    }
    private void PrintSectionHeader() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("***************      Student Methods (via Rest)     ***************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
    private void PrintSectionFooter() {
        System.out.println("***************   End Student Methods (via Rest)   ***************");
        System.out.println();
    }
}
