package EmployeeExercise;

public class Manager extends Employee{
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void draw() {
        System.out.println ("    /:''|    ");
        System.out.println ("   |: 66|_   ");
        System.out.println ("   C     _)  ");
        System.out.println ("    \\ ._|      ");
        System.out.println ("     ) /       ");
        System.out.println ("   /`\\       ");
        System.out.println ("   || |Y|       ");
        System.out.println ("   || |#|       ");
        System.out.println ("   || |#|       ");
        System.out.println ("   || |#|       ");
        System.out.println ("   :| |=:       ");
        System.out.println ("   ||_|,|      ");
        System.out.println ("   \\)))||     ");
        System.out.println ("    | ||       ");
        System.out.println ("    | ||       ");
        System.out.println ("    | ||       ");
        System.out.println ("    | ||       ");
        System.out.println ("    | ||       ");
        System.out.println ("    |_||__        ");
        System.out.println ("    (____))    ");
    }

    @Override
    public String toString() {
        return "#Manager#\n"+super.toString()+"Department: "+getDepartment()+"\n";
    }
}
