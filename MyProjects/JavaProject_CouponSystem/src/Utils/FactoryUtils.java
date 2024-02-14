package Utils;

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
}
