package JavaProject.CouponSystem2_Spring.Beans;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("administrator"),
    COMPANY("company"),
    CUSTOMER("customer"),
    GUEST("guest");

    private String roleName;

    UserRole(String role) {
        this.roleName = role;
    }
}
