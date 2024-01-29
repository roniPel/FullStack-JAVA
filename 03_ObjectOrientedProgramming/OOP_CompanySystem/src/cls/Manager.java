package cls;

public class Manager extends Employee{
    // Fields
    private String department;

    // Constructors
    public Manager() {
        super();
        setDepartment("Engineering");
    }
    public Manager(String name, Double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    // Getters/Setters

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Manager {" +
                super.toString() +
                ", department='" + department + '\'' +
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
        System.out.println("Manager");
    }
}
