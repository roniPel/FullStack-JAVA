package ReminderSystem;

import java.time.LocalDateTime;
import java.util.Set;

public class ReminderTask extends Thread{
    private boolean quit;
    private final Set<Reminder> reminders;

    public ReminderTask(Set<Reminder> reminders) {
        this.reminders = reminders;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public Set<Reminder> getReminders() {
        return reminders;
    }

    @Override
    public void run() {
        while(!quit) {
            for (Reminder r:reminders) {
                if( (r.getExpiration().isBefore(LocalDateTime.now()) ) && (r.isPoped() == false) ) {
                    System.out.println( r.getUrgency().getMessage() );
                    r.setPoped(true);
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void end() {
        setQuit(true);
    }
}
