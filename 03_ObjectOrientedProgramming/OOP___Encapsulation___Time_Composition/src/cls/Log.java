package cls;

import java.time.LocalDateTime;

public class Log {
    // Fields
    private long id;
    private String title;
    private String description;
    private long accountID;
    private LocalDateTime when;

    // Constructors
    public Log() {
        this(1234567890L, "Process executed", "The process was completed successfully",987654321L,LocalDateTime.now());
    }

    public Log(long id, String title, String description, long accountID) {
        this(id, title,description,accountID,LocalDateTime.now());
    }
    public Log(long id, String title, String description, long accountID, LocalDateTime when) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setAccountID(accountID);
        setWhen(when);
    }

    // Getters/Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Log {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", accountID=" + accountID +
                ", when=" + "D "+getWhen().getYear()+"-"+getWhen().getMonthValue()+"-"+getWhen().getDayOfMonth()+
                "T "+getWhen().getHour()+":"+getWhen().getMinute()+":"+getWhen().getSecond() +
                '}';
    }


    // User Methods
    public static void printLogs(Log[] logs) {
        for(Log log: logs) {
            System.out.println(log);
        }
    }
}
