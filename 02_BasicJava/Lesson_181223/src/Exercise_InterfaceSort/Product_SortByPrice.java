package Exercise_InterfaceSort;
import java.util.Comparator;

public class Product_SortByPrice implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPrice() - o2.getPrice()+0.5);
//        if(o1 instanceof Product && o2 instanceof Product) {
//            return (int) ( ( (Product) o1 ).getPrice()-( (Product) o2 ).getPrice() + 0.5);
//        }
//        return -1;
    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed();
    }
}
