package DateFunctions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFactory {
    public static LocalDate getLocalDate() {
        int day, month, year;
        // (int)(Math.random() * range) + min
        day = (int) (Math.random()*28+1);   // Between 1-28
        month = (int) (Math.random()*12+1); // Between 1-12
        year = (int) (Math.random()*51+1960); // Between 1960-2010
        return LocalDate.of(year,month,day);
    }
}
