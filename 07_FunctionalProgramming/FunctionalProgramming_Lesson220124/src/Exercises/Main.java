package Exercises;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        System.out.println(calcDistCM.apply(4,3));
    }
    private static Function<Integer,Integer> convertToCM = number -> number*100;
    private static BiFunction<Integer,Integer,Integer> calcDistance =
            (number1,number2) -> (int)(Math.sqrt(Math.pow(number1,2)+Math.pow(number2,2)));
    public static BiFunction<Integer,Integer,Integer> calcDistCM = calcDistance.andThen(convertToCM);
}
