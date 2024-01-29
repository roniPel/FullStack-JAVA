import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //File f = CreateNewFile();
        try {
//            // Create new file
//            String content = WriteContent();
//            String path = "src/Exercise1.txt";
//            File file = new File(path);
//            //Write in file
//            FileWriter fw = new FileWriter(file);
//            fw.write(content);
//            fw.close();

            String path = "src/Exercise1.txt";
            File file;
            FileWriter fw;
            Scanner sf, sc, userInput;
            char choice;
                // User menu
                do {
                    userInput = new Scanner(System.in);
                    file = new File(path);
                    //sf = new Scanner(file);
                    //sc = new Scanner(System.in);
                    //fw = new FileWriter(file,true);
                    System.out.println("Please select an option: r = read all data, a = add new line, " +
                            "f = find a text in the file, e = exit");
                    choice = userInput.next().charAt(0);
                    switch (choice) {
                        case 'r':
                            sf = new Scanner(file);
                            System.out.println("The file contains the following data: \n");
                            while (sf.hasNext()) {
                                System.out.println(sf.nextLine());
                            }
                            sf.close();
                            break;
                        case 'a':
                            sc = new Scanner(System.in);
                            fw = new FileWriter(file,true);
                            String addContent;
                            System.out.println("Please insert text to add to file: \n");
                            addContent = sc.nextLine();
                            fw.write("\n" + addContent);
                            fw.flush();
                            //fw.close();
                            sc.close();
                            break;
                        case 'f':
                            sf = new Scanner(file);
                            sc = new Scanner(System.in);
                            String findInFile, line;
                            int count = 0;
                            System.out.println("Please enter text you would like to search for in the file: \n");
                            findInFile = sc.nextLine();
                            while (sf.hasNext()) {
                                line = sf.nextLine();
                                for (int i = 0; i < line.length(); i++) {
                                    if (line.contains(findInFile)) {
                                        count ++;
                                        i += line.indexOf(findInFile)+findInFile.length();
                                        line = line.substring(i);
                                    }
                                }
                            }
                            System.out.println("The text: \n\""+findInFile+"\"\n"
                                    + "shows up "+count+" times");
                            sf.close();
                            sc.close();
                            break;
                        case 'e':
                            break;
                    }
                } while (choice != 'e');
            //fw.close();
            //sf.close();
            //sc.close();
            userInput.close();
        }
        catch (Exception exc) {
                System.out.println(exc);
        }
    }

    public static File CreateNewFile() {
        try {
            // Create new file
            String content = WriteContent();
            String path = "src/Exercise1.txt";
            File file = new File(path);

            // If file doesn't exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
                //Write in file
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                fw.write(content);
                fw.close();
                return file;
        }
        catch (Exception e) {
            System.out.println(e);
            return new File("Error.txt");
        }
    }

    public static String WriteContent() {
        String content = "";
        content = "How many roads does a man walk on\n" +
                "Before you can call him a man?\n" +
                "Yes, and how many seas must a white dove sail\n" +
                "Before she sleeps in the sand?\n" +
                "Yes and how many times must a man turn his head\n" +
                "Before he can see the sky?\n" +
                "The answer my friend\n" +
                "Is blowing in the wind\n" +
                "The answer is blowing in the wind\n";
        return content;
    }
}
