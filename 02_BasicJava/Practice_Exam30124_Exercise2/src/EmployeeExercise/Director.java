package EmployeeExercise;

public class Director extends Manager{
    private String group;

    public Director(String name, double salary, String department, String group) {
        super(name, salary, department);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void draw() {
        System.out.println ("  |\\/\\/\\/\\   ");
        System.out.println ("  |_______|   ");
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
        return "#Director#\n"+super.toString()+"Group: "+getGroup()+"\n";
    }
}
