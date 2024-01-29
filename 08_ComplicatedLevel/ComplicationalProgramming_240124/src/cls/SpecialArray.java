package cls;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpecialArray {
    // Fields
    private static SpecialArray instance = null;
    private static Integer[] integerArray;
    private static Integer [] backupIntegerArray;
    private static Timestamp[] timeStampArray;
    private static Timestamp deleteAllTimeStamp;
    private static final int size;
    public static Scanner scanner = new Scanner(System.in);

    private SpecialArray() {
        System.out.println("We created a brand new special array!");
        integerArray = new Integer[size];
        DeleteAll();
    }

    public static SpecialArray getInstance() {
        if(instance == null) {
            // Synchronized + double check on singleton getInstance
            synchronized (SpecialArray.class) {
                if(instance == null) {
                    instance = new SpecialArray();
                }
            }
        }
        return instance;
    }

    static {
        // Set array size (final)
        System.out.println("Please pick a size for your array");
        size = scanner.nextInt();
    }

    public static void GetIntegerArray() {
        System.out.println("The array is: ");
        System.out.println(Arrays.toString(integerArray));
    }

    public static void Add(Integer num, int index) {
        if(index >= integerArray.length) {
            System.out.println("No such index in the array. Addition failed.");
        }
        else {
            integerArray[index] = num;
            timeStampArray[index] = Timestamp.from(Instant.now());
            System.out.println("Item was added successfully");
        }
    }

    public static Integer GetNum(int index) {
        if(index >= integerArray.length) {
            System.out.println("No such index in the array");
            return null;
        }
        else {
            return integerArray[index];
        }
    }

    public static void Delete(int index) {
        if(index >= integerArray.length) {
            System.out.println("No such index in the array. Deletion failed.");
        }
        else {
            integerArray[index] = null;
            timeStampArray[index] = Timestamp.from(Instant.now());
            System.out.println("Item was deleted successfully");
        }
    }

    public static void DeleteAll() {
        // Create backup of integer Array
        backupIntegerArray = integerArray;
        // Empty integer and timestamp arrays
        integerArray = new Integer[size];
        timeStampArray = new Timestamp[size];
        deleteAllTimeStamp = Timestamp.from(Instant.now());
        System.out.println("All information was cleared at: "+deleteAllTimeStamp);
    }

    public static void AutoFillIntegerArray(int min, int max) {
        int range = max - min + 1;
        for (int i = 0; i < integerArray.length ; i++) {
            integerArray[i] = (int)(Math.random()*range + min);
        }
        System.out.println("The array was automatically filled with values between "+min+" and "+max);
    }
}
