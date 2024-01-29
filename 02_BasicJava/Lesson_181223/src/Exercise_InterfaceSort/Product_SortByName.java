package Exercise_InterfaceSort;

import java.util.Comparator;

public class Product_SortByName implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
//    public int compare(Object o1, Object o2) {
//        if(o1 instanceof Product && o2 instanceof Product){
//            return ((Product) o1).getName().compareTo(((Product) o2).getName());
//        }
//        return -1;
//    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed();
    }
}
