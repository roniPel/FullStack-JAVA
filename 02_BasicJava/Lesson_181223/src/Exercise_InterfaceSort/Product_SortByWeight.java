package Exercise_InterfaceSort;

import java.util.Comparator;

public class Product_SortByWeight implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int)(o1.getWeight()-o2.getWeight()+0.5);
    }
//    @Override
//    public int compare(Object o1, Object o2) {
//        if(o1 instanceof Product && o2 instanceof Product) {
//            return (int)( ( (Product) o1 ).getWeight()-( (Product) o2 ).getWeight() + 0.5);
//        }
//        return -1;
    }

//    @Override
//    public Comparator reversed() {
//        return Comparator.super.reversed();
//    }

