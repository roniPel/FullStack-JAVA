package cls;

public class Engineer extends Employee{
    // Fields
    private String speciality;

    // Constructors
    public Engineer() {
        super();
        setSpeciality("Web");
    }

    public Engineer(String name, Double salary, String speciality) {
        super(name, salary);
        this.speciality = speciality;
    }

    // Getters/Setters

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Engineer {" +
                super.toString() +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    // User Methods
    @Override
    public void draw() {
        System.out.println("   _   ");
        System.out.println(" _(\")_ ");
        System.out.println("(_ . _)");
        System.out.println(" / : \\ ");
        System.out.println("(_/ \\_)");
        System.out.println("Engineer");
    }
}
