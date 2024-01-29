package EmployeeExercise;

public class Engineer extends Employee{
    private String speciality;

    public Engineer(String name, double salary, String speciality) {
        super(name, salary);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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
        System.out.println ("|~~~`-`~~~|   ");
        System.out.println ("|         |    ");
        System.out.println ("|_________|    ");
        System.out.println ("|_________|    ");
        System.out.println ("    | ||       ");
        System.out.println ("    |_||__        ");
        System.out.println ("    (____))    ");
    }

    @Override
    public String toString() {
        return "#Engineer#\n"+super.toString()+"Speciality: "+getSpeciality()+"\n";
    }
}
