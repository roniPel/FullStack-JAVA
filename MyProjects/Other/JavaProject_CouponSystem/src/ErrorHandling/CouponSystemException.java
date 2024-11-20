package ErrorHandling;

/**
 * Coupons system exception class - for error handling
 */
public class CouponSystemException extends Exception {

    public CouponSystemException(Errors errors) {
        super(errors.getMessage());
    }
}
