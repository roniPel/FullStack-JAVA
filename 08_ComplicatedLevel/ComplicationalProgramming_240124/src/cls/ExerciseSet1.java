package cls;

import java.util.Arrays;
import java.util.Scanner;

public class ExerciseSet1 {

    public static void RunProgram_Ex1() {
        int randNum = GenerateRandNumber(1,9,6);
        FindMissingDigits(randNum);
    }
    public static void RunProgram_Ex4() {
        // Exercise 4:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select an array size: ");
        int size = scanner.nextInt();
        System.out.println("Please select minimum value: ");
        int min = scanner.nextInt();
        System.out.println("Please select maximum value: ");
        int max = scanner.nextInt();
        int[] myNumArray = CreateAndSortArray(size, min, max);
        int randNum = GenerateRandNumber(min,max,1);

        System.out.println("The array is: ");
        System.out.println(Arrays.toString(myNumArray));

        CountNumberOfFindings(myNumArray,randNum);
    }
    private static void CountNumberOfFindings(int[] myArray, int num) {
        // Use a Binary Search to find the number in the array
        int index = BinarySearch(myArray,num);
        int count = 1;
        // Check nearby indexes in both directions - up and down
        count += CheckNearbyIndexes(myArray,index, num, 1);
        count += CheckNearbyIndexes(myArray,index, num, -1);

        System.out.println("The number of times "+num+" shows up in the array is: "+count);
    }

    private static int BinarySearch(int[] myArray, int num) {
        int left = 0, right = myArray.length-1;
        while(left <= right) {
            int middle = left + (right-left)/2;

            // Check if num exists in the middle index
            if(myArray[middle] == num) {
                return middle;
            }

            // If num is greater, ignore left half
            if(myArray[middle] < num) {
                left = middle+1;
            }
            // If num is smaller, ignore right half
            else {
                right = middle-1;
            }

        }
        // In case the element was not present in the array
        return -1;
    }
    public static int CheckNearbyIndexes(int[] myArray, int idx, int num, int direction) {
        int count = 0;
        // Check the first index in the relevant direction
        if( (idx + direction > myArray.length-1) || (idx + direction < 0) )
            return count;
        if(found(myArray[idx + direction], num));
        else {
            return count;
        }
        do {
            if( (idx + direction > myArray.length-1) || (idx + direction < 0) )
                return count;
            else {
                count++;
                if (direction < 0) {
                    direction--;
                } else {
                    direction++;
                }
            }
        } while (found(myArray[idx + direction], num));
        return count;
    }

    private static boolean found(int itemInArray, int num) {
        return itemInArray == num;
    }
    private static int[] CreateAndSortArray(int size, int min, int max) {
        int [] myArray = new int[size];
        int range = max-min+1;
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = (int)(Math.random() * range) + min;
        }
        Arrays.sort(myArray);
        return myArray;
    }
    private static void FindMissingDigits(int input) {
        boolean[] containsDigit = new boolean[10];
        while(input != 0) {
            containsDigit[input%10] = true;
            input = input/10;
        }
        // Print missing digits
        System.out.println("Input number: "+input+" doesn't contain the digits: ");
        for (int i = 0; i < containsDigit.length; i++) {
            if(!containsDigit[i]){
                System.out.println(i+" ");
            }
        }
    }
    private static int GenerateRandNumber(int min, int max, int numberOfDigits) {
        int digits;
        int range = max - min + 1;
        digits = numberOfDigits;
        return (int)(Math.random()*digits*range + min);
    }

}
