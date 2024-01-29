import Shapes.Circle;
import Shapes.Rectangle;
import Shapes.Shape;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;
        //Shape[] arrShapes = new Shape[5];
        ArrayList<Shape> arrShapes = new ArrayList<>();
        int count = 0;
        do {
            System.out.print("please enter c-add circle, r-add rectangle, p-print, x-print radius, d-print width, " +
                    "s - save to file, l - load from file, e-exit: ");
            choice = scanner.next().charAt(0);
            switch (choice) {
                case 'c':
                    //if (count < arrShapes.size()) {
                    Circle c = new Circle(1, 2, 3);
                    arrShapes.add(c);
                    //arrShapes[count++] = c;
                    //}
                    break;
                case 'r':
                    //if (count < arrShapes.size())
                    arrShapes.add(new Rectangle(4, 5, 6, 7));
                    //arrShapes[count++] = new Rectangle(4, 5, 6, 7);
                    break;
                case 'p':
                    for (int i = 0; i < arrShapes.size(); i++) {
                        //arrShapes[i].Print();
                        arrShapes.get(i).Print();
                    }
                    break;
                case 'x':
                    for (int i = 0; i < arrShapes.size(); i++) {
                        if (arrShapes.get(i) instanceof Circle) {
                            Circle c1 = (Circle) arrShapes.get(i);
                            System.out.println(c1.getRadius());
                        }
                    }
                    break;
                case 'd':
                    for (int i = 0; i < arrShapes.size(); i++) {
                        if (arrShapes.get(i) instanceof Rectangle) {
                            Rectangle r1 = (Rectangle) arrShapes.get(i);
                            System.out.println(r1.getWidth());
                        }
                    }
                    break;
                case 's':
                    try {
                        FileOutputStream fs1 = new FileOutputStream("C:\\Users\\ronir\\IdeaProjects\\" +
                                "berore exercise inheritance - collections-20231220T100756Z-001\\Shapes.txt");
                        //DataOutputStream writer = new DataOutputStream(fs1);
                        //int totalSize = arrShapes.size();
                        //char type;
                        //writer.writeInt(arrShapes.size());
                        /*for (int i = 0; i < arrShapes.size(); i++) {
                            if(arrShapes.get(i) instanceof Circle) {    // Shape = Circle
                                Circle tempC = (Circle) arrShapes.get(i);
                                writer.writeChar('c');
                                writer.writeInt(tempC.getX());
                                writer.writeInt(tempC.getY());
                                writer.writeInt(tempC.getRadius());
                                //writer.writeInt(arrShapes.get(i).getX());
                                //writer.writeInt(arrShapes.get(i).getY());
                                //writer.writeInt(((Circle) arrShapes.get(i)).getRadius());
                            }
                            else if (arrShapes.get(i) instanceof Rectangle){  //Shape = rectangle
                                Rectangle tempR = (Rectangle) arrShapes.get(i);
                                writer.writeChar('r');
                                writer.writeInt(tempR.getX());
                                writer.writeInt(tempR.getY());
                                writer.writeInt(tempR.getWidth());
                                writer.writeInt(tempR.getHeight());
                                //writer.writeInt(arrShapes.get(i).getX());
                                //writer.writeInt(arrShapes.get(i).getY());
                                //writer.writeInt(((Rectangle) arrShapes.get(i)).getWidth());
                                //writer.writeInt(((Rectangle) arrShapes.get(i)).getHeight());
                            }
                        }*/
                        ObjectOutputStream writer = new ObjectOutputStream(fs1);
                        writer.writeObject(arrShapes);
                        writer.close();
                        fs1.close();
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                    break;
                case 'l':
                    arrShapes.clear();
                    try {
                        FileInputStream fs2 = new FileInputStream("C:\\Users\\ronir\\IdeaProjects\\" +
                                "berore exercise inheritance - collections-20231220T100756Z-001\\Shapes.txt");
                        /*DataInputStream reader = new DataInputStream(fs2);
                        int size = reader.readInt();
                        for (int i = 0; i < size; i++) {
                            char type = reader.readChar();
                            int x, y;
                            if(type == 'c') {  // Circle Shape
                                int radius;
                                x = reader.readInt();
                                y = reader.readInt();
                                radius = reader.readInt();
                                arrShapes.add(new Circle(x,y,radius));
                            }
                            else if (type == 'r') { // Rectangle Shape
                                int w, h;
                                x = reader.readInt();
                                y = reader.readInt();
                                w = reader.readInt();
                                h = reader.readInt();
                                arrShapes.add(new Rectangle(x,y,w,h));
                            }
                        }*/
                        ObjectInputStream reader = new ObjectInputStream(fs2);
                        arrShapes = (ArrayList<Shape>) reader.readObject();     // Casting to ArrayList because saving is to an Object
                        fs2.close();
                        reader.close();
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                    break;
            }
        } while (choice != 'e');
    }
}