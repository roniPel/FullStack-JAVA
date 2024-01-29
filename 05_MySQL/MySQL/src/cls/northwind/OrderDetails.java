package cls.northwind;

import java.util.Objects;

public class OrderDetails {
    // Fields
    private Integer odID;
    private Integer orderID;
    private Products productID;
    private Integer unitPrice;
    private Integer quantity;
    private Double discount;
    // Constructors

    public OrderDetails(Integer odID, Integer orderID, Products productID, Integer quantity, Double discount) {
        this.odID = odID;
        this.orderID = orderID;
        this.productID = productID;
        unitPrice = productID.getUnitPrice();
        this.quantity = quantity;
        this.discount = discount;
    }

    // Getters/Setters

    public Integer getOdID() {
        return odID;
    }

    public void setOdID(Integer odID) {
        this.odID = odID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
        unitPrice = productID.getUnitPrice();
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(odID, that.odID) && Objects.equals(orderID, that.orderID) && Objects.equals(productID, that.productID) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(quantity, that.quantity) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(odID, orderID, productID, unitPrice, quantity, discount);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "odID=" + odID +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}'+"\n";
    }

    // User Methods
}
