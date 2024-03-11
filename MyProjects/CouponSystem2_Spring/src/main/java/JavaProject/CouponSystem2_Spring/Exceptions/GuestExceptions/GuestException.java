package JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions;

/**
 * GuestException Class - used to manage Guest user exceptions
 */
public class GuestException extends Exception{
    public GuestException(GuestErrors guestErrors) {
        super(guestErrors.getMessage());
    }
}
