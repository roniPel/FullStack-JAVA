import ReminderSystem.ReminderSystem;

public class Run {
    public static void main(String[] args) {
        ReminderSystem reminderSystem = ReminderSystem.getInstance();
        reminderSystem.start();
    }
}
