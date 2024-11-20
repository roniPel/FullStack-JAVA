package JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions;

/**
 * AdminException Class - used to manage Admin user exceptions
 */
public class AdminException extends Exception{
    /**
     * Constructor for admin errors
     * @param adminErrors error received from system
     */
    public AdminException(AdminErrors adminErrors) {
        super(adminErrors.getMessage());
    }
}
