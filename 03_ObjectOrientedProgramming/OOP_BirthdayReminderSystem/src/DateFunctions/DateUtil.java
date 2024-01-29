package DateFunctions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // User Methods
    public static String beautifyDate(LocalDate date) {
        int year, day, month;
        return date.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") );
    }
}
