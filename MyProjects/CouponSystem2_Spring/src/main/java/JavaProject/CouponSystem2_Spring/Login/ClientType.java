package JavaProject.CouponSystem2_Spring.Login;

/**
 * ClientType Class (Enum) - lists user (client) types available in the system
 */
public enum ClientType {
    ADMINISTRATOR("Administrator"),
    COMPANY("Company"),
    CUSTOMER("Customer"),
    GUEST("Guest");

    private String message;

    /**
     * Constructor which inserts the relevant message into each Client type.
     * @param message String message relevant for each error
     */
    ClientType(String message) {
        this.message = message;
    }
}
