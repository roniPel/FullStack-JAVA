import java.util.Scanner;

public class App {
    static boolean isCancelled = false;
    public static void main(String[] args) {

        // Thread - find prime numbers between 1 and 300,000
        Thread prime = new Thread(new Runnable() {
            @Override
            public void run() {
                int idx = 300000;
                boolean isPrime = false;
                for (int i = 1; i <= idx; i++) {
                    switch (i) {
                        case 1:
                            isPrime = false;
                            break;
                        case 2:
                            isPrime = true;
                            break;
                        default:
                            for (int j = 2; j < i; j++) {
                                if (i % j == 0) {
                                    isPrime = false;
                                    break;
                                }
                                else if(j == i-1)
                                    isPrime = true;
                            }
                    }
                    if(isPrime) {
                        System.out.println(i);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(isCancelled) // Break out of thread
                        break;
                }
            }
        });
        prime.start();

        // Main Thread
        String name = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please enter your name: ");
            name = scanner.nextLine();
            if (name.length() > 3) {
                System.out.println("Hi! Nice to see you, " + name);
                isCancelled = true;
            }
        } while (name.length() < 4);
    }
}
