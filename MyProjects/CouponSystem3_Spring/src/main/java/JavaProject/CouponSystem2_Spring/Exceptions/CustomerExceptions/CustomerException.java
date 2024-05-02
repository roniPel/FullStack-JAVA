package JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions;

/**
 * CustomerException Class - used to manage Customer user exceptions
 */
public class CustomerException extends Exception{
    /**
     * Constructor for customer errors
     * @param customerErrors error received from system
     */
    public CustomerException(CustomerErrors customerErrors) {
        super(customerErrors.getMessage());
    }
}
