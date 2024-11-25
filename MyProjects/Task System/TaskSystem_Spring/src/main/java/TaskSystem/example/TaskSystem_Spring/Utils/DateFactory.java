package TaskSystem.example.TaskSystem_Spring.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFactory {

    /**
     * @return random LocalDate value
     */
    public static LocalDate getRandomLocalDate() {
        int day = (int) (Math.random() * 28 + 1); // Day: between 1->28
        int month = (int) (Math.random() * 12 + 1); // Month: between 1->12
        int year = (int) (Math.random() * 4 + 2025);  //Year: between 2025-2028
        return LocalDate.of(year,month,day);
    }

    /**
     * Converts date object into a readable String format
     * @param date a LocalDate object
     * @return a String format of the provided date object
     */
    public static String beautifyDate(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Converts date into SQL date format
     * @param date a LocalDate object
     * @return a String format matching MySQL DB
     */
    public static String covertToSQLdate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd") );
    }
}
