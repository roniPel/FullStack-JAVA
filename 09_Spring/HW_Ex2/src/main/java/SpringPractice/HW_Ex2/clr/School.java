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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Class.forName;

@Component
@Order(1)
public class School implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LecturerRepository lecturerRepository;

    private static Scanner scanner = new Scanner(System.in);
    private int numStudentsPerLecturer = 2;
    private int studentCounter = 1;
    private int lecturerCounter = 1;

    @Override
    public void run(String... args) throws Exception {
        StartSystem();
        
        mainProgram();
    }

    private void UserMenu() {
        System.out.println("\n\n\nSchool Menu\n" +
                "================================\n"+
                "1. Add lecturer (and students)\n"+
                "2. Get Lecturer by ID\n"+
                "3. Get student list by Lecturer ID\n"+
                "4. Get all students\n"+
                "5. Get all grades\n"+
                "6. Get grades by date\n"+
                "7. Get grades between dates\n"+
                "\n"+
                "0. Exit System"
        );
    }

    private void mainProgram() {
        while(true){
            UserMenu();
            String input = scanner.nextLine();
            System.out.println();
            switch(input) {
                case "1":
                    AddLecturer();
                    break;
                case "2":
                    GetLecturerByID();
                    break;
                case "3":
                    GetStudentsByLecturerID();
                    break;
                case "4":
                    GetAllStudents();
                    break;
                case "5":
                    GetAllGrades();
                    break;
                case "6":
                    GetGradesByDate();
                    break;
                case "7":
                    GetGradesBetweenDates();
                    break;
                case "0":
                    EndSystem();
                    break;
                default:
                    System.out.println("Not an option!!!");
            }
        }
    }

    private void GetGradesBetweenDates() {
        System.out.println("Please insert a start date: YYYY/MM/DD");
        String date1 = scanner.nextLine();
        Date startDateSql = Date.valueOf(date1);

        System.out.println("Please insert an end date: YYYY/MM/DD");
        String date2 = scanner.nextLine();
        Date endDateSql = Date.valueOf(date2);

        System.out.println("The students with dates matching inserted range are: ");
        System.out.println(studentRepository.findByEndDateBetween(startDateSql,endDateSql));
    }

    private void GetGradesByDate() {
        System.out.println("Please insert a date: YYYY/MM/DD");
        String date = scanner.nextLine();
        Date dateSql = Date.valueOf(date);

        System.out.println("The students with dates matching "+ date+ "are: ");
        System.out.println(studentRepository.findByEndDateBetween(dateSql,dateSql));
    }

    private void GetAllGrades() {
        List<Student> allStudents = studentRepository.findAll();
        System.out.println("All the grades in the system are: ");
        allStudents.forEach(student -> System.out.println(student.getGrade()));
    }

    private void GetAllStudents() {
        System.out.println("All the students in the system are: ");
        System.out.println(studentRepository.findAll());
    }

    private void GetStudentsByLecturerID() {
        Lecturer lecturer = lecturerRepository.findById(GetLecturerRandId()).get();
        System.out.println("The students listed under Lecturer "+lecturer.getName()+" are: ");
        System.out.println(lecturer.getStudents());
    }

    private void GetLecturerByID() {
        System.out.println("Please select an ID: ");
        System.out.println("The chosen lecturer is: ");
        System.out.println(lecturerRepository.findById(GetLecturerRandId()));
    }

    private Integer GetLecturerRandId() {
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        return GetRandIdFromDB(allLecturers.size());
    }

    private Integer GetStudentRandId() {
        List<Student> allStudents = studentRepository.findAll();
        return GetRandIdFromDB(allStudents.size());
    }

    private Integer GetRandIdFromDB(int size) {
        return (int)(Math.random()*size)+1;
    }

    private void AddLecturer() {
        // Add 2 students
        ArrayList <Student> students = AddStudents();
        // Add lecturer and link the students
        Lecturer l1 = Lecturer.builder()
                .name("Lecturer"+lecturerCounter++)
                .salary(GetRandSalary())
                .id(GetRandId())
                .students(students)
                .build();

        lecturerRepository.save(l1);
    }

    private long GetRandId() {
        return (int)(Math.random()*(50000000-10000000)+10000000);
    }

    private double GetRandSalary() {
        return Math.random()*(50000-10000)+10000;
    }

    private ArrayList<Student> AddStudents() {
        Student s1 = Student.builder()
                .name("Student"+studentCounter++)
                .grade(GetRandGrade())
                .endDate(GetRandDate())
                .build();

        Student s2 = Student.builder()
                .name("Student"+studentCounter++)
                .grade(GetRandGrade())
                .endDate(GetRandDate())
                .build();

        studentRepository.save(s1);
        studentRepository.save(s2);

        ArrayList <Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        return students;
    }

    private Date GetRandDate() {
        int day = (int)(Math.random()*28+1); // 1->28
        int month = (int)(Math.random()*12+1); // 1->12
        int year = (int)(Math.random()*51+1960);  //1960->2010
        return Date.valueOf(LocalDate.of(year,month,day));
    }

    private int GetRandGrade() {
        return (int)(Math.random()*30)+70;  // Between 70 & 100
    }

    private void EndSystem() {
        scanner.close();
        System.out.println("**** That's all folks! ****");
        System.exit(0);
    }

    private void StartSystem() {
        System.out.println("**** WELCOME! ****");
//        if(lecturerRepository.findAll().isEmpty()) {
//            CreateAndFillDB();
//        }
    }

    private void CreateAndFillDB() {

        // Create Students in DB
        ArrayList<Student> allStudents = CreateStudents();

        // Create lecturers in DB
        CreateLecturers(numStudentsPerLecturer,allStudents);

    }

    private void CreateLecturers(int numStudentsPerLecturer, ArrayList<Student> allStudents) {
        ArrayList<Student> students = CreateRandStudentGroup(numStudentsPerLecturer, allStudents);
        Lecturer Zeev = Lecturer.builder()
                .id(11111111)
                .name("Zeev")
                .salary(25000.00)
                .students(students)
                .build();

        students.clear();
        students = CreateRandStudentGroup(numStudentsPerLecturer,allStudents);
        Lecturer Amit = Lecturer.builder()
                .salary(20000.00)
                .id(2222222)
                .name("Amit")
                .students(students)
                .build();

        lecturerRepository.save(Zeev);
        lecturerRepository.save(Amit);
    }

    private ArrayList<Student> CreateRandStudentGroup(int numStudentsPerLecturer, ArrayList<Student> allStudents) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < numStudentsPerLecturer; i++) {
            int rand = (int)(Math.random()*allStudents.size())+1;
            students.add(allStudents.get(rand));
        }
        return students;
    }

    private ArrayList<Student> CreateStudents() {
        Student Roni = Student.builder()
                .id(123456789)
                .name("Roni")
                .grade(92)
                .endDate(Date.valueOf(LocalDate.of(2024,6,25)))
                .build();

        Student Maya = Student.builder()
                .id(222555888)
                .name("Maya")
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

        studentRepository.save(Roni);
        studentRepository.save(Noga);
        studentRepository.save(Maya);
        studentRepository.save(Omer);

        ArrayList<Student> students = new ArrayList<>();
        students.add(Roni);
        students.add(Maya);
        students.add(Omer);
        students.add(Noga);

        return students;
    }

}
