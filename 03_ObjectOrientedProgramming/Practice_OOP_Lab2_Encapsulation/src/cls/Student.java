package cls;

public class Student {
    // Fields
    private int id;
    private String lastName;
    private String firstName;
    private float avg;
    private String teacher;

    // Constructors
    public Student() {
        this(12345,"Armstrong","Niel", 95.67F,"NASA");
    }
    public Student(int id, String lastName, String firstName, float avg, String teacher) {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setAvg(avg);
        setTeacher(teacher);
    }

    // Getters/Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", avg=" + avg +
                ", teacher='" + teacher + '\'' +
                '}';
    }


    // User Methods
    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
