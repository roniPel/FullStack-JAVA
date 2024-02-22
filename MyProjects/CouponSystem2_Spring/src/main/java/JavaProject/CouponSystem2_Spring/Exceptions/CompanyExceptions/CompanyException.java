package JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;

public class CompanyException extends Exception{
    public CompanyException(CompanyErrors companyErrors) {
        super(companyErrors.getMessage());
    }

}
