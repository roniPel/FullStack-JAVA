package cls;

public class Rectangle {
    // fields
    private Double a;
    private Double b;
    private String color;

    // constructors

    public Rectangle() {
        this(1.0,1.0,"White");
    }
    public Rectangle(Double a, Double b) {
        this(a,b,"White");
    }
    public Rectangle(Double a, Double b, String color) {
        setA(a);
        setB(b);
        setColor(color);
    }

    // getters/setters

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    // toString, hashCode, equal

    @Override
    public String toString() {
        return "Rectangle {" +
                "a=" + a +
                ", b=" + b +
                ", color='" + color + '\'' +
                '}'+"\n";
    }

    // user methods
    public static void printRectangles(Rectangle[] rectangles) {
        for (Rectangle rectangle : rectangles) {
            System.out.println(rectangle);
        }
    }
}
