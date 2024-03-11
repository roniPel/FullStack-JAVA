package JavaProject.CouponSystem2_Spring.Services.GuestService;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Guest Service Implementation for Coupon System 2
 */
@Service
@RequiredArgsConstructor
@Getter
public class GuestServiceImpl implements GuestService{

    //Todo - finish writing guest service Impl methods
    @Override
    public boolean Login(String email, String password) throws CustomerException, AdminException, CompanyException {
        return false;
    }
}
