package ReminderSystem;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public boolean equals(LocalDateTime ldt1, LocalDateTime ldt2) {
        if( ldt1.getYear() != ldt2.getYear() )
            return false;
        else if( ldt1.getMonth() != ldt2.getMonth() )
            return false;
        else if( ldt1.getDayOfMonth() != ldt2.getDayOfMonth() )
            return false;
        else if( ldt1.getHour() != ldt2.getHour() )
            return false;
        else if( ldt1.getMinute() != ldt2.getMinute() )
            return false;
        return true;
    }

    public static Set<Reminder> init(int len) {
        Set<Reminder> setReminders = new HashSet<>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < len; i++) {
            setReminders.add(new Reminder("Reminder"+(i+1),now.plusMinutes(i),Urgency.Rand()));
        }
        return setReminders;
    }
}
