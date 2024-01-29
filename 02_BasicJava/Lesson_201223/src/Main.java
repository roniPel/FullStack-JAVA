import ClassExercise_Singleton.Calc;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /*// Class Exercise - calculate age based on birth date
        int age = CalcAge(scan);
        System.out.println("Your current age is: "+age+" years");*/

        // Class Exercise - 'change' the previous exercise with circle/square - so that user can add as many items as he wants
        // Open project: before exercise inheritance - collections-20231220T100756Z-001

        // Class Exercise - Singleton
        int a = 86, b = 3;
        Calc calc = Calc.getInstance();
        System.out.println("a = "+a+", b = "+b);
        System.out.println("Add: "+a+"+"+b+" = "+Calc.Add(a,b));
        System.out.println("Subtract: "+a+"-"+b+" = "+Calc.Subtract(a,b));
        System.out.println("Multiply: "+a+"*"+b+" = "+Calc.Multiply(a,b));
        System.out.println("Divide: "+a+"/"+b+" = "+Calc.Divide(a,b));
    }
    public static int CalcAge(Scanner scanner) {
        LocalDate now1 = LocalDate.now();
        int birthDay, birthMonth, birthYear, age;
        System.out.println("Please enter your birth day, month, and year");
        birthDay = scanner.nextInt();
        birthMonth = scanner.nextInt();
        birthYear = scanner.nextInt();
        LocalDate dateOfBirth = LocalDate.of(birthYear,birthMonth,birthDay);
        age = Period.between(dateOfBirth,now1).getYears();
        //age = now1.compareTo(dateOfBirth);
        return age;
    }
}