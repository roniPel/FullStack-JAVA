import Lambda.Calc;

import java.time.LocalTime;
import java.util.Scanner;

public class App {
    static boolean flagPause = false;
    public static void main(String[] args) {
        // Class Exercise - Threads
//        Thread x = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!flagPause) {
//                    LocalTime locTime = LocalTime.now();
//                    System.out.println(String.format("%02d:%02d:%02d",locTime.getHour(),
//                            locTime.getMinute(),locTime.getSecond()));
//                    try {
//                        Thread.sleep(5000);
//                    }
//                    catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                System.out.println("END of thread");
//            }
//        });
//        x.start();
//        Scanner scanner = new Scanner(System.in);
//        char choice;
//        do {
//            System.out.println("Please select one of the options: a - calculate the sum of 2 numbers, e - exit");
//            choice = scanner.next().charAt(0);
//            switch(choice) {
//                case 'a':
//                    //Scanner num = new Scanner(System.in);
//                    int num1,num2;
//                    System.out.println("Please write 2 numbers (Separated by an 'enter'): ");
//                    num1 = scanner.nextInt();
//                    num2 = scanner.nextInt();
//                    System.out.println("Sum = "+(num1+num2));
//                    break;
//                case 'e':
//                    System.out.println("END of main");
//                    scanner.close();
//                    flagPause = true;
//                    return;
//            }
//        } while(true);

        // Class Exercise - Lambda
        Calc c1 = (x,y) -> {return x + y;};
        Calc c2 = (d,e) ->  d - e;
        Calc c3 = (f,g) -> {return f * g;};
        Calc c4 = (m,n) -> m / n;

        System.out.println(c1.fun1(2,8));
        System.out.println(c2.fun1(14,6));
        System.out.println(c3.fun1(8,7));
        System.out.println(c4.fun1(68,9));
    }
}