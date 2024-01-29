package cls;

public class Light {
    // Fields
    private int totalHours;
    private String color;
    private double cost;

    // Constructors
    public Light() {
        this(24,"White",19.99);
    }
    public Light(int totalHours, String color) {
        this(totalHours,color,19.99);
    }
    public Light(int totalHours, double cost) {
        this(totalHours,"White",cost);
    }
    public Light(int totalHours, String color, double cost) {
        setTotalHours(totalHours);
        setColor(color);
        setCost(cost);
    }

    // Getters/Setters

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Light {" +
                "totalHours=" + totalHours +
                ", color='" + color + '\'' +
                ", cost=" + cost +
                '}'+"\n";
    }

    // User Methods
    public static void printLights(Light[] lights) {
        for (Light light : lights) {
            System.out.println(light);
        }
    }
}
