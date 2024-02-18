package Job;

import Beans.Coupon;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Coupons expiration daily job - for deleting expired coupons
 */
public class CouponExpirationDailyJob implements Runnable{

    private CouponsDAO couponsDAO;
    private boolean quit;
    private final Integer TIME = 1000*60*60*24; // 24 hours

    /**
     * Daily job Constructor
     */
    public CouponExpirationDailyJob() {
        this.couponsDAO = new CouponsDB_DAO();
        setQuit(false);
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    /**
     * Checks if coupon is expired
     * @param coupon 'Coupon' object to check
     * @return True if expired, false if not expired
     */
    private boolean isExpired(Coupon coupon) {
        return LocalDate.now().isAfter( coupon.getEndDate() );
    }

    /**
     * Deletes a coupon (by using the couponsDAO methods)
     * @param coupon 'Coupon' object to be deleted
     * @throws CouponSystemException If we get any exception.  Details are provided
     */
    private void DeleteCoupon(Coupon coupon) throws CouponSystemException {
        // Verify deletion of coupon and of purchase history - performed by DB cascade config.
        couponsDAO.DeleteCoupon(coupon.getId());
    }

    @Override
    public void run() {
        // Part 1 - Get all coupons from DB
        ArrayList<Coupon> deletedCoupons = new ArrayList<>();
        ArrayList<Coupon> coupons = null;
        try {
            coupons = couponsDAO.GetAllCoupons();
        } catch (CouponSystemException e) {
            System.out.println(Errors.GENERAL_SYSTEM_ERROR.getMessage()+ e);
            stop();
        }
        // Part 2 - Verify 'coupons' is not null
        if(coupons == null) {
            //throw new CouponSystemException(Errors.EMPTY_OR_NULL.getMessage() + e)
            System.out.println(Errors.EMPTY_OR_NULL.getMessage()) ;
        }
        else {

            // Part 3 - Thread loop - continues as long as 'quit' is false
            while (!quit) {
                for (Coupon coupon : coupons) {
                    if (isExpired(coupon)) {
                        try {
                            DeleteCoupon(coupon);
                            deletedCoupons.add(coupon);
                        } catch (CouponSystemException e) {
                            System.out.println((Errors.THREAD_ERROR.getMessage()));
                            stop();
                        } catch (NullPointerException e) {
                            //throw new CouponSystemException(Errors.EMPTY_OR_NULL.getMessage() + e);
                            System.out.println((Errors.EMPTY_OR_NULL.getMessage()));
                            stop();
                        }
                    }
                }
                PrintCouponJobStatus(deletedCoupons);
                try {
                    Thread.sleep(TIME);
                } catch (InterruptedException e) {
                    //throw new CouponSystemException(Errors.THREAD_ERROR.getMessage());
                    System.out.println(Errors.THREAD_ERROR.getMessage() + e);
                    stop();
                }
            }
        }
    }

    /**
     * Prints the coupon expiration job status summary.
     * @param deletedCoupons - Array of expired coupons that were deleted from DB
     */
    private void PrintCouponJobStatus(ArrayList<Coupon> deletedCoupons) {
        System.out.println("--------     COUPON JOB STATUS     --------");
        System.out.println("| Coupon Expiration Job ran successfully.  |");
        System.out.println("| The coupons that were deleted due to     |\n" +
                           "| expiration date are:                     |");
        System.out.println(deletedCoupons);
        System.out.println("--------    END COUPON JOB STATUS  --------");
        System.out.println();
    }

    public void stop() {
        setQuit(true);
    }
}
