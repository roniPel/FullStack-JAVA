package cls;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Flight {
    // Fields
    private String code;
    private String company;
    private String from;
    private String to;
    private LocalDateTime when;

    // Constructors

    public Flight(String code, String company, String from, String to) {
        this.code = code;
        this.company = company;
        this.from = from;
        this.to = to;
        setWhen(LocalDateTime.now());
    }

    public Flight(String code, String company, String from, String to, LocalDateTime when) {
        this.code = code;
        this.company = company;
        this.from = from;
        this.to = to;
        this.when = when;
    }

    // Getters/Setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        return "Flight {" +
                "code='" + code + '\'' +
                ", company='" + company + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", when=" + "D "+getWhen().getYear()+"-"+getWhen().getMonthValue()+"-"+getWhen().getDayOfMonth()+
                        "T "+getWhen().getHour()+":"+getWhen().getMinute()+":"+getWhen().getSecond() +
                '}';
    }


    // User Methods
    public static void printFlights(Flight[] flights) {
        for(Flight flight: flights) {
            System.out.println(flight);
        }
    }
}
