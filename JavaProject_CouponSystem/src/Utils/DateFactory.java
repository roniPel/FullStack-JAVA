package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFactory {
    public static LocalDate getLocalDate(boolean endDate){
        int day = (int) (Math.random() * 28 + 1); // Day: between 1->28
        int month = (int) (Math.random() * 12 + 1); // Month: between 1->12
        int year = (int) (Math.random() * 6 + 2019);  //Year: between 2019-2024
        if(endDate) {
            year = (int) (Math.random() * 4 + 2025);  //Year: between 2025-2028
        }
        return LocalDate.of(year,month,day);
    }

    public static String beautifyDate(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate getEndDate(LocalDate startDate) {
       //Todo - is 'EndDate' needed?  If so, complete
        return LocalDate.now();
    }

    public static String covertToSQLdate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd") );
    }

}
