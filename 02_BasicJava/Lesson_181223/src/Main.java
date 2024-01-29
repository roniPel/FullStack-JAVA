import Exercise_Animals.Chair;
import Exercise_Animals.*;
import Exercise_InterfaceSort.Product;
import Exercise_InterfaceSort.Product_SortByName;
import Exercise_InterfaceSort.Product_SortByPrice;
import Exercise_InterfaceSort.Product_SortByWeight;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
/*        // Exercise - Animals
        Object[] oArr = new Object[4];
        for (int i = 0; i < oArr.length; i++) {
            oArr[i] = InitializeNewObject();
        }
        PrintObjectArray(oArr);*/

        // Exercise - Interface Sort
        Product[] pArr = {  new Product("Flour", 6.45,100),
                            new Product("Oil", 7.95,1000),
                            new Product("Butter",9.80,200),
                            new Product("Tomato",10.15,1000),
                            new Product("Cucumber",6.23, 850),
                            new Product("Onion",3.85,450) };

        // User menu
        char choice, order;
        do {
            System.out.println("Sort by (please select): p = Price, w = weight, n = Name, e = Exit");
            choice = scan.next().charAt(0);
            switch(choice) {
                case 'p':
                    Arrays.sort(pArr, new Product_SortByPrice());
                    break;
                case 'w':
                    Arrays.sort(pArr, new Product_SortByWeight());
                    break;
                case 'n':
                    Arrays.sort(pArr, new Product_SortByName());
                    break;
                case 'e':
                    return;
                default:
                    System.out.println("Not one of the options.");
                    break;
            }
            System.out.println("Please select order - a = Ascending, d = Descending");
            order = scan.next().charAt(0);
            if(order == 'd') {
                pArr = ReverseOrder(pArr);
            }
            System.out.println(Arrays.toString(pArr));
        } while (choice != 'e');
    }
    public static Product[] ReverseOrder(Product[] arr) {
        Product[] objArr = new Product[arr.length];
        for (int i = 0; i < arr.length ; i++) {
            objArr[i] = arr[arr.length-1-i];
        }
        return objArr;
    }
    public static void PrintObjectArray(Object[] arr) {
        for (int j = 0; j < arr.length; j++) {
            CheckAnimal(arr[j]);
            System.out.println(arr[j].getClass().getName());
            System.out.println();
        }
    }
    public static Object InitializeNewObject() {
        Object obj = new Object();
        int choice = (int) (Math.random()*4);
        switch(choice) {
            case 0:
                obj = new Cow();
                break;
            case 1:
                obj = new Horse();
                break;
            case 2:
                obj = new Dog();
                break;
            case 3:
                obj = new Chair();
                break;
        }
        return obj;
    }
    public static void CheckAnimal(Object o) {
        if (o instanceof IAnimal) {
            ((IAnimal) o).MakeSound();
        }
        else {
            System.out.println("Not an animal");
        }
    }
}
