package SpringPractice.HW_Ex2.clr;

import SpringPractice.HW_Ex2.Beans.Lecturer;
import SpringPractice.HW_Ex2.Beans.Student;
import SpringPractice.HW_Ex2.repositories.LecturerRepository;
import SpringPractice.HW_Ex2.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Order(1)
public class CreateFillDB implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public void run(String... args) throws Exception {
        Student Roni = Student.builder()
                .id(123456789)
                .name("Roni")
                .grade(92)
                .endDate(Date.valueOf(LocalDate.of(2024,6,25)))
                .build();

        Student Maya = Student.builder()
                .id(222555888)
                .name("Shani")
                .grade(99)
                .endDate(Date.valueOf(LocalDate.of(2028,10,15)))
                .build();

        Student Omer = Student.builder()
                .id(777888598)
                .name("Omer")
                .grade(98)
                .endDate(Date.valueOf(LocalDate.of(2025,7,1)))
                .build();

        Student Noga = Student.builder()
                .name("Noga")
                .endDate(Date.valueOf(LocalDate.of(2027,4,28)))
                .id(147852369)
                .grade(100)
                .build();

        ArrayList<Student> students = new ArrayList<>();
        students.add(Roni);
        students.add(Maya);
        students.add(Omer);
        students.add(Noga);

        Lecturer Zeev = Lecturer.builder()
                .id(789456123)
                .salary(25000)
                .students(students)
                .build();
    }
}
