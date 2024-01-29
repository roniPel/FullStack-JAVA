package cls;

import java.time.LocalDate;

public class Airplane {
    // Fields
    private long id;
    private int numOfSeats;
    private LocalDate dateOfCreation;
    private LocalDate lastRepairing;

    // Constructors
    public Airplane() {
        this(123456789L, 400,LocalDate.now(),LocalDate.of(2020,12,23));
    }
    public Airplane(long id, int numOfSeats, LocalDate dateOfCreation) {
        this(id, numOfSeats, dateOfCreation, LocalDate.of(2018,5,8));
    }

    public Airplane(long id, int numOfSeats, LocalDate dateOfCreation, LocalDate lastRepairing) {
        setId(id);
        setNumOfSeats(numOfSeats);
        setDateOfCreation(dateOfCreation);
        setLastRepairing(lastRepairing);
    }


    // Getters/Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getLastRepairing() {
        return lastRepairing;
    }

    public void setLastRepairing(LocalDate lastRepairing) {
        this.lastRepairing = lastRepairing;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Airplane {" +
                "id=" + id +
                ", numOfSeats=" + numOfSeats +
                ", dateOfCreation=" + dateOfCreation +
                ", lastRepairing=" + lastRepairing +
                '}';
    }


    // User Methods
    public static void printAirplanes(Airplane[] airplanes) {
        for(Airplane airplane: airplanes) {
            System.out.println(airplane);
        }
    }
}
