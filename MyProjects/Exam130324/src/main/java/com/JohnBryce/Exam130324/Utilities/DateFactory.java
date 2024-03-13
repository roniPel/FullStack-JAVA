package com.JohnBryce.Exam130324.Utilities;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
@Component
public class DateFactory {
    public Date GetRandomDate() {
        int day = (int) (Math.random() * 28 + 1); // Day: between 1->28
        int month = (int) (Math.random() * 12 + 1); // Month: between 1->12
        int year = (int) (Math.random() * 6 + 2019);  //Year: between 2019-2024
        LocalDate date = LocalDate.of(year,month,day);
        return Date.valueOf(date);
    }
}
