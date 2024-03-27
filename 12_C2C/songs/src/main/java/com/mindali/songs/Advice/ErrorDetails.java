package JavaProject.CouponSystem2_Spring.Advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorDetails Class - used to provide Error details for Advice classes for clients
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String key;
    private String value;
}
