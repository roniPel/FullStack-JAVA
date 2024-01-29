package cls.northwind;

import java.time.LocalDate;
import java.util.Objects;

public class Orders {
    // Fields
    private Integer orderID;
    private Customers customerID;
    private Employees employeeID;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private Integer shipVia;
    private Integer freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    // Constructors

    public Orders(Integer orderID, Customers customerID, Employees employeeID, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, Integer shipVia, Integer freight, String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }


    // Getters/Setters

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    public Employees getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employees employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderID, orders.orderID) && Objects.equals(customerID, orders.customerID) && Objects.equals(employeeID, orders.employeeID) && Objects.equals(orderDate, orders.orderDate) && Objects.equals(requiredDate, orders.requiredDate) && Objects.equals(shippedDate, orders.shippedDate) && Objects.equals(shipVia, orders.shipVia) && Objects.equals(freight, orders.freight) && Objects.equals(shipName, orders.shipName) && Objects.equals(shipAddress, orders.shipAddress) && Objects.equals(shipCity, orders.shipCity) && Objects.equals(shipRegion, orders.shipRegion) && Objects.equals(shipPostalCode, orders.shipPostalCode) && Objects.equals(shipCountry, orders.shipCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", customerID=" + customerID +
                ", employeeID=" + employeeID +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", shipVia=" + shipVia +
                ", freight=" + freight +
                ", shipName='" + shipName + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipRegion='" + shipRegion + '\'' +
                ", shipPostalCode='" + shipPostalCode + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                '}'+"\n";
    }

    // User Methods
}
