package cls.northwind;

import java.util.Objects;

public class Shippers {
    // Fields
    private Integer shipperID;
    private String companyName;
    private String phone;

    // Constructors

    public Shippers(Integer shipperID, String companyName, String phone) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
    }

    // Getters/Setters

    public Integer getShipperID() {
        return shipperID;
    }

    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shippers shippers = (Shippers) o;
        return Objects.equals(shipperID, shippers.shipperID) && Objects.equals(companyName, shippers.companyName) && Objects.equals(phone, shippers.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipperID, companyName, phone);
    }

    @Override
    public String toString() {
        return "Shippers{" +
                "shipperID=" + shipperID +
                ", companyName='" + companyName + '\'' +
                ", phone='" + phone + '\'' +
                '}'+"\n";
    }

    // User Methods
}
