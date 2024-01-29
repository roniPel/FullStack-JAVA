package SchoolExercise;

public class Student extends Person {
    public Student(String name, int age, Grade[] grades) {
        super(name, age);
        this.grades = grades;
    }

    private Grade[] grades;

    public Grade[] getGrades() {
        return grades;
    }

    public void setGrades(Grade[] grades) {
        if(grades.length == 6)
            this.grades = grades;
        else
            System.out.println("The inserted grades array doesn't match the system requirements: size = 6");
    }
    @Override
    public String toString() {
        return super.toString();
    }
    public String PrintGrades() {
        String gradesPrint = "";
        for (Grade g: getGrades()) {
            gradesPrint += g.toString();
            System.out.println(g);
        }
        return gradesPrint;
    }
}
