package EmployeeExercise;

import java.time.LocalDateTime;

public class CompanySystemException extends Exception{
    public CompanySystemException(String message) {
        super(message+" has occurred in "+Helper.format( LocalDateTime.now() ) );
    }
}
