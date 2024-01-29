package cls.northwind;

import java.util.Objects;

public class Products {
    // Fields
    private Integer productID;
    private String productName;
    private Suppliers supplierID;
    private Categories categoryID;
    private String quantityPerUnit;
    private Integer unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer reorderLevel;
    private Boolean discontinued;

    // Constructors

    public Products(Integer productID, String productName, Suppliers supplierID, Categories categoryID, String quantityPerUnit, Integer unitPrice, Integer unitsInStock, Integer unitsOnOrder, Integer reorderLevel, Boolean discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }


    // Getters/Setters

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Suppliers getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Suppliers supplierID) {
        this.supplierID = supplierID;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Integer getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Integer unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(productID, products.productID) && Objects.equals(productName, products.productName) && Objects.equals(supplierID, products.supplierID) && Objects.equals(categoryID, products.categoryID) && Objects.equals(quantityPerUnit, products.quantityPerUnit) && Objects.equals(unitPrice, products.unitPrice) && Objects.equals(unitsInStock, products.unitsInStock) && Objects.equals(unitsOnOrder, products.unitsOnOrder) && Objects.equals(reorderLevel, products.reorderLevel) && Objects.equals(discontinued, products.discontinued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
    }

    @Override
    public String toString() {
        return "Products{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", supplierID=" + supplierID +
                ", categoryID=" + categoryID +
                ", quantityPerUnit='" + quantityPerUnit + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                ", unitsOnOrder=" + unitsOnOrder +
                ", reorderLevel=" + reorderLevel +
                ", discontinued=" + discontinued +
                '}'+"\n";
    }
    // User Methods
}
