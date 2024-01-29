package Join;

import java.util.Objects;

public class Question10 {
    // Fields
    private Integer productID;
    private String categoryDescription;
    private String country;

    // Constructors

    public Question10(Integer productID, String categoryDescription, String country) {
        this.productID = productID;
        this.categoryDescription = categoryDescription;
        this.country = country;
    }


    // Getters/Setters

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question10 that = (Question10) o;
        return Objects.equals(productID, that.productID) && Objects.equals(categoryDescription, that.categoryDescription) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, categoryDescription, country);
    }

    @Override
    public String toString() {
        return "Question10{" +
                "productID=" + productID +
                ", category='" + categoryDescription + '\'' +
                ", country='" + country + '\'' +
                '}' + "\n";
    }

    // User Methods
}
