package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.RestMethods.GuestTestMethods_Rest;
import JavaProject.CouponSystem2_Spring.Clr_Test.TestMethods.ServiceMethods.GuestTestMethods_Services;
import JavaProject.CouponSystem2_Spring.Login.LogonUtil;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import JavaProject.CouponSystem2_Spring.Services.GuestService.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Clr Tester - used to test Guest user methods
 */
@Component
@RequiredArgsConstructor
@Order(5)
public class Clr_GuestTester implements CommandLineRunner {

    @Autowired
    private LogonUtil logonUtil;
    @Autowired
    private GuestTestMethods_Services guestTestMethods_services;
    @Autowired
    private GuestTestMethods_Rest guestTestMethods_rest;
    @Autowired
    private GuestService guestService; //- Preparation for Client Side (section 3)
    @Override
    public void run(String... args) throws Exception {

    }
    //Todo - write tester

}
