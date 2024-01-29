package cls;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Coupon {
    // Fields
    private int id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private double price;

    // Constructors
    public Coupon( String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price) {
        this(123456789,title, description, startDate, endDate, amount, price);
    }

    public Coupon(int id, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setStartDate(startDate);
        setEndDate(endDate);
        setAmount(amount);
        setPrice(price);
    }


    // Getters/Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null) {
            setStartDate(LocalDate.now());
        }
        else {
            this.startDate = startDate;
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate == null) {
            setEndDate(LocalDate.now().plusWeeks(1));
        }
        else {
            this.endDate = endDate;
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Coupon {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }


    // User Methods
    public static void printCoupons(Coupon[] coupons) {
        for(Coupon coupon: coupons) {
            System.out.println(coupon);
        }
    }
}
