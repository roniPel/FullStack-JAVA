package JavaProject.CouponSystem2_Spring.Services.GuestService;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.GuestExceptions.GuestException;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Guest Service Implementation for Coupon System 2
 */
@Service
@RequiredArgsConstructor
@Getter
public class GuestServiceImpl implements GuestService{
    private final CouponRepository couponRepo;
    private final CustomerRepository customerRepo;

    @Override
    public List<Coupon> GetAllCoupons() {
        return couponRepo.findAll();
    }

    @Override
    public Coupon GetCouponById(int couponId) throws GuestException {
        return couponRepo.findById(couponId).orElseThrow(
                () ->new GuestException(GuestErrors.COUPON_DOES_NOT_EXIST));
    }

}
