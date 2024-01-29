package cls.northwind;

import java.util.Objects;

public class Categories {
    // Fields
    private Integer categoryID;
    private String categoryName;
    private String description;
    private String picture;

    // Constructors

    public Categories(Integer categoryID, String categoryName, String description, String picture) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        this.picture = picture;
    }

    // Getters/Setters

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categories that = (Categories) o;
        return Objects.equals(categoryID, that.categoryID) && Objects.equals(categoryName, that.categoryName) && Objects.equals(description, that.description) && Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, categoryName, description, picture);
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}'+ "\n";
    }

    // User Methods
}
