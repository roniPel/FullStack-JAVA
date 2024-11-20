package JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions;

/**
 * CompanyException Class - used to manage Company user exceptions
 */
public class CompanyException extends Exception{
    /**
     * Constructor for company errors
     * @param companyErrors error received from system
     */
    public CompanyException(CompanyErrors companyErrors) {
        super(companyErrors.getMessage());
    }

}
