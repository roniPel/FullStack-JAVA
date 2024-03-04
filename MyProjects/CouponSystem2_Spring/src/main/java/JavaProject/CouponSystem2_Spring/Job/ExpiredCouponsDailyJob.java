package JavaProject.CouponSystem2_Spring.Job;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpiredCouponsDailyJob {
    @Autowired
    private CouponRepository couponRepo;
    @Scheduled(cron = "${ExpiredCouponsJob_TIME}")
    public void CheckForExpiredCoupons() {
        System.out.println("Expired Coupons Job Started");
        // Part 1 - Get Expired coupons from DB
        List<Coupon> expiredCoupons = couponRepo.GetExpiredCoupons();
        // Part 2 - verify 'coupons' array is not empty
        if( expiredCoupons.isEmpty() );
        else {  // Part 3 - Delete all expired coupons in list
            couponRepo.deleteAllInBatch(expiredCoupons);
            PrintCouponJobStatus(expiredCoupons);
        }
    }

    /**
     * Prints the coupon expiration job status summary.
     * @param deletedCoupons - Array of expired coupons that were deleted from DB
     */
    private void PrintCouponJobStatus(List<Coupon> deletedCoupons) {
        System.out.println("--------     COUPON JOB STATUS     --------");
        System.out.println("| Coupon Expiration Job ran successfully.  |");
        System.out.println("| The coupons that were deleted due to     |\n" +
                "| expiration date are:                     |");
        deletedCoupons.forEach(System.out::println);
        System.out.println("--------    END COUPON JOB STATUS  --------");
        System.out.println();
    }
}
