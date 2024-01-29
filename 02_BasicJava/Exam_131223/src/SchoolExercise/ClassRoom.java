package SchoolExercise;

public class ClassRoom {
    public ClassRoom(String name, Teacher teacher, Student[] students) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    private String name;
    private Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        if(students.length == 15)
            this.students = students;
        else
            System.out.println("The inserted students array doesn't match the system requirements: size = 15");
    }

    private Student[] students;

    @Override
    public String toString() {
        return "-- Classroom: "+getName()+" --\n*** Teacher ***\n"+getTeacher()+"*** Students ***\n"+getStudents()+"\n";
    }
}
