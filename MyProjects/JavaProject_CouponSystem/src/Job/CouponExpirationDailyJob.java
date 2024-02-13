package Job;

import Beans.Coupon;
import DataBase.DAO.CouponsDAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;
import java.time.LocalDate;
import java.util.ArrayList;

public class CouponExpirationDailyJob implements Runnable{

    private CouponsDAO couponsDAO;
    private boolean quit;

    //Todo - change time to 24 hours (below)
    private final Integer TIME = 1000*60*2; // 1000*60*60*24; // 24 hours

    public CouponExpirationDailyJob() {
        this.couponsDAO = new CouponsDB_DAO();
        this.quit = false;
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
        return LocalDate.now().isEqual( coupon.getEndDate() );
    }

    /**
     * Deletes a coupon (by using the couponsDAO methods)
     * @param coupon 'Coupon' object to be deleted
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private void DeleteCoupon(Coupon coupon) throws CouponSystemException {
        // Verify deletion of coupon and of purchase history - performed by DB cascade config.
        couponsDAO.DeleteCoupon(coupon.getId());
    }

    @Override
    public void run() {

        //Todo - Delete sout below:
        System.out.println("Entered job");
        // Part 1 - Get all coupons from DB
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
    public void stop() {
        setQuit(true);
    }
}
