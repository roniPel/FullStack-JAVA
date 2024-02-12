package Job;

import Beans.Coupon;
import DataBase.DAO.CouponsDAO;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class CouponExpirationDailyJob implements Runnable{

    private CouponsDAO couponsDAO;
    private boolean quit = false;
    private final Integer TIME = 1000*60*60*24; // 24 hours
    private ArrayList<CouponSystemException> exceptions;
    //Todo - ask Zeev:
    // 1. How to configure 'run' so that Exceptions don't cause errors in 'run()' Messages instead of exceptions
    // 2. Should tester send 'messages' informing various methods were successful? YES


    //Todo - Delete 'exceptions' array, delete all exception gathering in 'run' and switch to exception throwing
    //Todo - add 'Sleep' - to 'Test'?

    public CouponExpirationDailyJob(CouponsDAO couponsDAO, boolean quit) {
        this.couponsDAO = couponsDAO;
        this.quit = quit;
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

        // Part 1 - Get all coupons from DB
        ArrayList<Coupon> coupons = null;
        try {
            coupons = couponsDAO.GetAllCoupons();
        } catch (CouponSystemException e) {
            System.out.println("System threw an exception! Details: "+ e);
            stop();
        }
        // Part 2 - Verify 'coupons' is not null
        if(coupons == null) {
            //throw new CouponSystemException(Errors.EMPTY_OR_NULL.getMessage() + e)
            exceptions.add( new CouponSystemException(Errors.EMPTY_OR_NULL.getMessage()) );
            System.out.println("System threw an exception! \n");
        }

        // Part 3 - Thread loop - continues as long as 'quit' is false
        while (!quit) {
                for (Coupon coupon: coupons) {
                    if(isExpired(coupon)) {
                        try {
                            DeleteCoupon(coupon);
                        } catch (CouponSystemException e) {
                            exceptions.add( new CouponSystemException(Errors.THREAD_ERROR.getMessage()) );
                            System.out.println("System threw an exception! \n");
                            stop();
                        }
                        catch(NullPointerException e) {
                            //throw new CouponSystemException(Errors.EMPTY_OR_NULL.getMessage() + e);
                            exceptions.add( new CouponSystemException(Errors.EMPTY_OR_NULL.getMessage()) );
                            System.out.println("System threw an exception! \n");
                            stop();
                        }
                    }
                }
            try {
                Thread.sleep(TIME);
            } catch (InterruptedException e) {
                //throw new CouponSystemException(Errors.THREAD_ERROR.getMessage());
                exceptions.add( new CouponSystemException(Errors.THREAD_ERROR.getMessage()) );
                System.out.println("System threw an exception! \n");
                stop();
            }
            System.out.println("The exceptions caught by the daily job are: ");
            System.out.println(exceptions);
        }
    }
    public void stop() {
        setQuit(true);
    }
}
