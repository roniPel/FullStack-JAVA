package ScalarFunctions;

import java.util.Objects;

public class Question13 {
    // Fields
    private String product;
    private Integer fullPrice;

    // Constructors

    public Question13(String product, Integer fullPrice) {
        this.product = product;
        this.fullPrice = fullPrice;
    }


    // Getters/Setters

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice) {
        this.fullPrice = fullPrice;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question13 that = (Question13) o;
        return Objects.equals(product, that.product) && Objects.equals(fullPrice, that.fullPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, fullPrice);
    }

    @Override
    public String toString() {
        return "Question13{" +
                "product='" + product + '\'' +
                ", fullPrice=" + fullPrice +
                '}' + "\n";
    }

    // User Methods
}
