import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

//        FileWriter w=new FileWriter("data1.txt");
//        w.write("Animals are multicellular\n,");
//        w.write("eukaryotic organisms in the biological kingdom Animalia\n");
//        w.write("With few exceptions\n");
//        w.write(", animals consume organic material\n");
//        w.close();

        Scanner scanerReader;
        File f;
        FileWriter writer;
        Scanner scannerUserInput = new Scanner(System.in);
        char choice;
        do {
            System.out.print("please enter r-read, w-add new text line,f-find word in file, e-exit: ");
            choice = scannerUserInput.nextLine().charAt(0);
            switch (choice) {
                case 'r':
                    f = new File("data1.txt");
                    scanerReader = new Scanner(f);
                    while(scanerReader.hasNext()){
                        System.out.println(scanerReader.nextLine());
                    }
                    scanerReader.close();
                    break;
                case 'w':
                    System.out.print("please enter new data: ");
                    String userData2=scannerUserInput.nextLine();
                    writer=new FileWriter("data1.txt",true);
                    writer.write(userData2+"\n");
                    writer.close();
                    break;
                case 'f':
                    System.out.print("please enter word for search: ");
                    String userData3=scannerUserInput.nextLine();
                    int count=0;
                    f = new File("data1.txt");
                    scanerReader = new Scanner(f);
                    while(scanerReader.hasNext()){
                        String line1 = scanerReader.nextLine();
                        int location=-1;
                        do {
                            location = line1.indexOf(userData3, ++location);
                            if (location > -1) {
                                count++;
                            }
                        }while(location>-1);
                    }
                    scanerReader.close();
                    System.out.println(count);
                    break;
            }

        } while (choice != 'e');
    }
}