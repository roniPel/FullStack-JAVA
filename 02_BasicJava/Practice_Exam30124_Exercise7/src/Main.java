import ExceptionsExercise.InvalidEmployeeCertException;
import ExceptionsExercise.SalesPerson;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InvalidEmployeeCertException {
        SalesPerson sp1 = new SalesPerson("Johnny", 34,7824,"356-akt");
        //System.out.println(sp1);
//        SalesPerson sp2 = new SalesPerson("Hagit",27,46000,"892-#a;");
//        System.out.println(sp2);
//        SalesPerson sp3 = new SalesPerson("Daniel",15,5849,"a58-789NEQ");
//        System.out.println(sp3);
//        SalesPerson sp4 = new SalesPerson("Ariel",61,12000,"265?#a2");
//        System.out.println(sp4);
        SalesPerson sp5 = new SalesPerson("Mindy", 58,8962,"904-ase");
        //System.out.println(sp5);
        SalesPerson sp6 = new SalesPerson("Artium", 19,10589,"124-ygj");
        //System.out.println(sp6);
        SalesPerson sp7 = new SalesPerson("Lilly", 24,35863,"904-ase");
        //System.out.println(sp7);

        SalesPerson[] arrSP = new SalesPerson[] {sp1,sp7,sp6,sp5};
        System.out.println("The unsorted array is: ");
        PrintArray(arrSP);
        Arrays.sort(arrSP);
        System.out.println();
        System.out.println("The sorted array is: ");
        PrintArray(arrSP);
    }
    public static void PrintArray(SalesPerson[] arrSP) {
        for (int i = 0; i < arrSP.length; i++) {
            System.out.println(arrSP[i]);
        }
    }
}
