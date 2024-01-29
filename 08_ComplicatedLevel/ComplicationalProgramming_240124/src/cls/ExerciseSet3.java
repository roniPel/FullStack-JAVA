package cls;

import java.util.Scanner;

public class ExerciseSet3 {
    public static void RunProgram() {
        // Create an instance of SpecialArray singleton
        SpecialArray myArray = SpecialArray.getInstance();
        Scanner scanner = SpecialArray.scanner;
        boolean toExit = false;
        int choice;

        while(!toExit) {
            choice = userChoice(scanner);
            switch (choice) {
                case 1:
                    AddItem(scanner);
                    break;
                case 2:
                    DeleteItem(scanner);
                    break;
                case 3:
                    GetNumberInArray(scanner);
                    break;
                case 4:
                    SpecialArray.GetIntegerArray();
                    break;
                case 5:
                    SpecialArray.DeleteAll();
                    break;
                case 6:
                    AutoFillArray(scanner);
                    break;
                case 0:
                    toExit = true;
                    break;
            }
        }
        scanner.close();
        System.out.println("Thats all, folks!");
    }

    private static void AutoFillArray(Scanner scanner) {
        System.out.println("Please select a minimum value: ");
        System.out.println("> ");
        int min = scanner.nextInt();
        System.out.println("Please select a maximum value: ");
        System.out.println("> ");
        int max = scanner.nextInt();
        SpecialArray.AutoFillIntegerArray(min, max);
    }

    private static void GetNumberInArray(Scanner scanner) {
        System.out.println("Please select an index of an item you wish to view: ");
        System.out.println("> ");
        int index = scanner.nextInt();
        if(SpecialArray.GetNum(index) == null) {
            System.out.println("There is no number at this index");
        }
        else {
            int num = SpecialArray.GetNum(index);
            System.out.println("The number at index " + index + " is: " + num);
        }
    }

    private static void DeleteItem(Scanner scanner) {
        System.out.println("Please select an index of an item you wish to delete: ");
        System.out.println("> ");
        int index = scanner.nextInt();
        SpecialArray.Delete(index);
    }

    private static void AddItem(Scanner scanner) {
        System.out.println("Please insert an index: ");
        System.out.println("> ");
        int index = scanner.nextInt();
        System.out.println("Please insert a value: ");
        System.out.println("> ");
        int num = scanner.nextInt();
        SpecialArray.Add(num,index);
    }

    private static int userChoice(Scanner scanner) {
        userMenu();
        return scanner.nextInt();
    }

    public static void userMenu() {
        System.out.println("** User menu **");
        System.out.println("1 - Add an item to the array");
        System.out.println("2 - Delete an item from the array");
        System.out.println("3 - Get an item from the array");
        System.out.println("4 - Show the entire array");
        System.out.println("5 - Clear the entire array");
        System.out.println("6 - Autofill the entire array");
        System.out.println();
        System.out.println("0 - Exit");
        System.out.print("> ");
    }
}
