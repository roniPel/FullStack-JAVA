package cls;

public class Secretary extends Employee{
    // Fields
    private String office;

    // Constructors
    public Secretary() {
        super();
        setOffice("Room 1");
    }

    public Secretary(String name, Double salary, String office) {
        super(name, salary);
        this.office = office;
    }

    // Getters/Setters

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Secretary {" +
                super.toString() +
                ", office='" + office + '\'' +
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
        System.out.println("Secretary");
    }
}
