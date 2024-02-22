package JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions;

import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyErrors;

public class CustomerException extends Exception{
    public CustomerException(CustomerErrors customerErrors) {
        super(customerErrors.getMessage());
    }
}
