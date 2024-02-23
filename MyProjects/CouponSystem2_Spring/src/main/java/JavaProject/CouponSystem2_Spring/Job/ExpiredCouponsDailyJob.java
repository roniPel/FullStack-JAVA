package JavaProject.CouponSystem2_Spring.Job;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExpiredCouponsDailyJob {
    //Todo - write job as aspect?
    // Use Time like Zeev recommended (Cron Schedule)
    // Prepare SQL query - copy from system1 - Complexity = O(1)
    // Zeev's example: Spring 07/02
}
