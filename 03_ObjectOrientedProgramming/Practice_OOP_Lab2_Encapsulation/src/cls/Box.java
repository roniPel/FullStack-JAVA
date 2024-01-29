package cls;

public class Box {
    // Fields
    private int a;
    private int b;
    private int c;

    // Constructors
    public Box() {
        this(3,4,5);
    }
    public Box(int a, int b, int c) {
        setA(a);
        setB(b);
        setC(c);
    }

    // Getters/Setters
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Box {" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    // User Methods
    public static void printBoxes(Box[] boxes) {
        for (Box box : boxes) {
            System.out.println(box);
        }
    }
}
