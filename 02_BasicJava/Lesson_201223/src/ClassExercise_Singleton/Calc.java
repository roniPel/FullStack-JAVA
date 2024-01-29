package ClassExercise_Singleton;

public class Calc {
    private Calc() {}
    private static Calc instance1 = new Calc();
    public static Calc getInstance() {
        return instance1;
    }
    public static int Add(int a, int b) {
        return a+b;
    }

    public static int Subtract(int a, int b) {
        return a-b;
    }

    public static int Multiply(int a, int b) {
        return a*b;
    }

    public static int Divide(int a, int b) {
        if (b != 0)
            return a/b;
        return 0;
    }
}
