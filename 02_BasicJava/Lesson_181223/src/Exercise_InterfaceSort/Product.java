package Exercise_InterfaceSort;

public class Product implements Comparable{
    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private double weight;

    @Override
    public String toString() {
        return "Name: "+getName()+"\tPrice: "+getPrice()+"\tWeight: "+getWeight()+"\n";
    }

    @Override
    public int compareTo(Object o) {
        Product other = (Product)o;
        return (int)(this.getPrice()-other.getPrice());
    }
}
