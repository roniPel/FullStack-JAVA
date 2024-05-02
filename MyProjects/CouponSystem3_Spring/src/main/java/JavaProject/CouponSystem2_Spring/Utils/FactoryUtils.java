package JavaProject.CouponSystem2_Spring.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Contains methods for displaying prices
 */
public class FactoryUtils {
    /**
     * Changes price format into organized decimal String format
     * @param price price in double format
     * @return price in String format
     */
    public static String beautifyPrice(Double price) {
        String pattern = "###,###.##";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(price);
    }

    /**
     * Rounds a value to the number of decimal places specified in params
     * @param value Value to be rounded
     * @param places Number of decimal places after the decimal point
     * @return A rounded 'value'
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
