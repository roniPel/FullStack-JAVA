package ErrorHandling;

import CompanyTools.Company;
import Utils.Helper;

import java.time.LocalDateTime;

public class CompanySystemException extends Exception{

    // Constructors
    public CompanySystemException(String msg) {
        super ( msg+" has occurred in "+ Helper.format(LocalDateTime.now()) );
    }
}
