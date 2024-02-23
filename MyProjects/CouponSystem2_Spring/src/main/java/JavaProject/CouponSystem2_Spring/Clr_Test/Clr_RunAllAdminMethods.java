package JavaProject.CouponSystem2_Spring.Clr_Test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Clr_RunAllAdminMethods implements CommandLineRunner {
    //Todo - Create a login manager.  How to define?
    //public LoginManager loginManager = LoginManager.builder().build();
    public boolean isLoggedOn = false;

    @Override
    public void run(String... args) throws Exception {

    }
}
