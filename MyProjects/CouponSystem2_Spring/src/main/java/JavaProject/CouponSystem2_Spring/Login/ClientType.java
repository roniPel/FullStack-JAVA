package JavaProject.CouponSystem2_Spring.Login;

import lombok.Getter;

/**
 * ClientType Class (Enum) - lists user (client) types available in the system
 */
@Getter
public enum ClientType {
    ADMINISTRATOR("Administrator"),
    COMPANY("Company"),
    CUSTOMER("Customer"),
    GUEST("Guest");

    private String name;

    /**
     * Constructor which inserts the relevant message into each Client type.
     * @param name String message relevant for each error
     */
    ClientType(String name) {
        this.name = name;
    }
}
