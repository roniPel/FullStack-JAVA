import cls.*;

import static cls.Box.printBoxes;
import static cls.Light.printLights;
import static cls.Printer.printPrinters;
import static cls.Rectangle.printRectangles;
import static cls.Student.printStudents;

public class Main {
    public static void main(String[] args) {
/*        // Exercise 1
        int size = 3;
        Rectangle[] rectangles = new Rectangle[] {
                new Rectangle(),
                new Rectangle(3.5,5.2),
                new Rectangle(7.8,6.2, "Green")
        };
        printRectangles(rectangles);
        */

        /*// Exercise 2
        int size = 3;
        Printer[] printers = new Printer[] {
                new Printer(),
                new Printer(2987340L,"HP247", "HP"),
                new Printer(897762209873L,"IJ476", "Inkjet",false)
        };
        printPrinters(printers);*/

        /*// Exercise 3
        int size = 3;
        Light[] lights = new Light[] {
                new Light(),
                new Light(24,"Blue"),
                new Light(54,45.23),
                new Light(75,"Purple",78.42)
        };
        printLights(lights);*/

        /*// Exercise 4
        int size = 2;
        Box[] boxes = new Box[] {
                new Box(),
                new Box(6,7,8)
        };
        printBoxes(boxes);*/

        // Exercise 5
        int size = 2;
        Student[] students = new Student[] {
                new Student(),
                new Student(223344,"Twain","Shanaia",78.34F,"Dolly Parton")
        };
        printStudents(students);
    }
}
