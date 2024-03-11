package JavaProject.CouponSystem2_Spring.Services.GuestService;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Services.ClientService;

/**
 * Guest Service interface - Lists relevant functionalities for Guest users
 */
public interface GuestService extends ClientService {
    @Override
    boolean Login(String email, String password) throws CustomerException, AdminException, CompanyException;

    //Todo - write guest user service methods + service Impl
}
