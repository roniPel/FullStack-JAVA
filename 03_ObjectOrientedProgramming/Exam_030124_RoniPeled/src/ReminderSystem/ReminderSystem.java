package ReminderSystem;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ReminderSystem {
    private static Scanner scanner;
    private static ReminderSystem instance=null;
    private Set<Reminder> reminders;
    private ReminderTask task;

    private ReminderSystem() {
        this.reminders = Utils.init(10);
    }
    public static Scanner getScanner() {
        return scanner;
    }
    public static ReminderSystem getInstance() {
        if (instance==null){
            instance = new ReminderSystem();
        }
        return instance;
    }

    public void setTask(ReminderTask task) {
        this.task = task;
    }
    public void start() {
        System.out.println("*** Here we go! ***");
        scanner = new Scanner(System.in);
        setTask(new ReminderTask(reminders) );
        task.start();
        program();
    }
    public void program() {
        char choice;
        do {
            menu();
            choice = getScanner().next().charAt(0);
            switch(choice) {
                case 'a':
                    addReminder();
                    break;
                case 'v':
                    displaySorted();
                    break;
                case 'e':
                    end();
                    break;
            }

        } while(choice != 'e');
    }
    public void menu() {
        System.out.println("Please select an option: a - add new reminder, v - view reminders, e - exit");
    }
    public void end() {
        scanner.close();
        task.end();
    }
    public void displaySorted() {
        TreeSet<Reminder> treeSet = new TreeSet<>(reminders);
        System.out.println(treeSet);
    }
    public void addReminder() {
        String text;
        int minutes;
        Scanner scan = new Scanner(System.in);
        LocalDateTime expiration;
        System.out.println("Please provide text: ");
        text = scan.nextLine();
        System.out.println("Please provide # of minutes (from now): ");
        minutes = getScanner().nextInt();
        expiration = LocalDateTime.now().plusMinutes(minutes);
        reminders.add(new Reminder(text,expiration,Urgency.Rand()));
    }
}