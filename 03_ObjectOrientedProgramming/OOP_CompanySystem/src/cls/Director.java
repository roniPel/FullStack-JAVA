package cls;

public class Director extends Manager{
    // Fields
    private String group;

    // Constructors
    public Director() {
        super();
        setGroup("Production");
    }

    public Director(String name, Double salary, String department, String group) {
        super(name, salary, department);
        this.group = group;
    }

    // Getters/Setters

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Director {" +
                super.toString() +
                ", group='" + group + '\'' +
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
        System.out.println("Director");
    }
}
