package JavaProject.CouponSystem2_Spring.Login;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * ClientType Class (Enum) - lists user (client) types available in the system
 */

//@Component
@Getter
public enum ClientType {
    Administrator,
    Company,
    Customer,
    Guest;

    //Todo - return client type to old format?

    /*
    // Old:
    ADMINISTRATOR("Administrator"),
    COMPANY("Company"),
    CUSTOMER("Customer"),
    GUEST("Guest");

    private String name;

    ClientType(String name) {
        this.name = name;
    }

     */

}
