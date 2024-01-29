import cls.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cls.Airplane.printAirplanes;
import static cls.Coupon.printCoupons;
import static cls.Flight.printFlights;
import static cls.Log.printLogs;
import static cls.Person.display;

public class Main {
    public static void main(String[] args) {

        /*// Exercise 1:
        Flight[] flights = new Flight[]{
                new Flight("AR702", "Lufthansa", "Berlin", "London"),
                new Flight("AG358", "British Airways", "London", "Canada", LocalDateTime.now())
        };
        printFlights(flights);*/

        /*// Exercise 2:
        Airplane[] airplanes = new Airplane[] {
                new Airplane(),
                new Airplane(887766554L,376, LocalDate.of(2000,8,18)),
                new Airplane(1122334455L,289,LocalDate.of(2012,3,30),LocalDate.of(2023,11,1))
        };
        printAirplanes(airplanes);*/

        /*// Exercise 3:
        Log[] logs = new Log[] {
                new Log(),
                new Log(2233445566L, "Log2","Description Log 2",9988776655L),
                new Log(234234234L, "Log3", "Description Log3", 987987987L,LocalDateTime.now().minusYears(3))
        };
        printLogs(logs);*/

        /*// Exercise 4:
        Coupon[] coupons = new Coupon[] {
                new Coupon(22334455, "Title1", "Description 1",LocalDate.now(),LocalDate.now(),12,450.25),
                new Coupon("Title2", "Description2",LocalDate.now(),LocalDate.now(),60,15.89)
        };
        printCoupons(coupons);*/

        // Exercise 5:
        Person person1 = new Person();
        Person person2 = new Person("Aviv", LocalDate.of(1994,8,19));
        System.out.println( display(person1) );
        System.out.println( display(person2) );
    }
}
