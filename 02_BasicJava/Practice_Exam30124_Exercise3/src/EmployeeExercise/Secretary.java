package EmployeeExercise;

public class Secretary extends Employee{
    private String office;

    public Secretary(String name, double salary, String office) {
        super(name, salary);
        this.office = office;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public void draw() {
        System.out.println ("    /:''|    ");
        System.out.println ("   |: 66|_   ");
        System.out.println ("  /     _)   ");
        System.out.println ("  ||\\ ._|     ");
        System.out.println ("  JJ ) /JJ     ");
        System.out.println ("   /`\\      ");
        System.out.println ("   || | )       ");
        System.out.println ("   || ||       ");
        System.out.println ("   || ||       ");
        System.out.println ("   ||_|_\\      ");
        System.out.println ("   /     \\    ");
        System.out.println ("  |       |    ");
        System.out.println ("  |       |    ");
        System.out.println ("  |       |    ");
        System.out.println ("  |       |    ");
        System.out.println ("  |_______|    ");
        System.out.println ("    | ||       ");
        System.out.println ("    |_||__        ");
        System.out.println ("    (____))    ");
    }

    @Override
    public String toString() {
        return "#Secretary#\n"+super.toString()+"Office: "+getOffice()+"\n";
    }
}
