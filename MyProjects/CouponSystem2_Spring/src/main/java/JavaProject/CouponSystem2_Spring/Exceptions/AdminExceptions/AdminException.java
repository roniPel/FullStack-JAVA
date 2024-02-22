package JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions;

public class AdminException extends Exception{
    public AdminException(AdminErrors adminErrors) {
        super(adminErrors.getMessage());
    }
}
