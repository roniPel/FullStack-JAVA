package ReminderSystem;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class Reminder implements Comparable {
    private String text;
    private LocalDateTime expiration;
    private Urgency urgency;
    private boolean isPoped;

    public Reminder(String text, LocalDateTime expiration, Urgency urgency, boolean isPoped) {
        this.text = text;
        this.expiration = expiration;
        this.urgency = urgency;
        this.isPoped = isPoped;
    }

    public Reminder(String text, LocalDateTime expiration, Urgency urgency) {
        this.text = text;
        this.expiration = expiration;
        this.urgency = urgency;
    }

    public Reminder(String text, LocalDateTime expiration) {
        this.text = text;
        this.expiration = expiration;
        setUrgency(Urgency.Normal);
    }

    public Reminder(String text, Urgency urgency) {
        this.text = text;
        this.urgency = urgency;
        setExpiration(LocalDateTime.now());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public boolean isPoped() {
        return isPoped;
    }

    public void setPoped(boolean poped) {
        isPoped = poped;
    }

    @Override
    public int compareTo(Object o) {

        if(o instanceof Reminder){
            return this.getExpiration().compareTo(((Reminder) o).getExpiration());
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return Objects.equals(text, reminder.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Reminder {" +
                "text='" + text + '\'' +
                ", expiration=" + expiration +
                ", urgency=" + urgency +
                ", isPoped=" + isPoped +
                '}' + "\n";
    }
}
