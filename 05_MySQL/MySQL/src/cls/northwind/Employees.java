package cls.northwind;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Employees {
    // Fields
    private Integer employeeID;
    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private Integer extension;
    private String photo;
    private String notes;
    private Integer reportsTo;

    // Constructors

    public Employees(Integer employeeID, String lastName, String firstName, String title, String titleOfCourtesy, LocalDate birthDate, LocalDate hireDate, String address, String city, String region, String postalCode, String country, String homePhone, Integer extension, String photo, String notes, Integer reportsTo) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.photo = photo;
        this.notes = notes;
        this.reportsTo = reportsTo;
    }


    // Getters/Setters

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public Integer getExtension() {
        return extension;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }


    // toString, HashCode, Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return Objects.equals(employeeID, employees.employeeID) && Objects.equals(lastName, employees.lastName) && Objects.equals(firstName, employees.firstName) && Objects.equals(title, employees.title) && Objects.equals(titleOfCourtesy, employees.titleOfCourtesy) && Objects.equals(birthDate, employees.birthDate) && Objects.equals(hireDate, employees.hireDate) && Objects.equals(address, employees.address) && Objects.equals(city, employees.city) && Objects.equals(region, employees.region) && Objects.equals(postalCode, employees.postalCode) && Objects.equals(country, employees.country) && Objects.equals(homePhone, employees.homePhone) && Objects.equals(extension, employees.extension) && Objects.equals(photo, employees.photo) && Objects.equals(notes, employees.notes) && Objects.equals(reportsTo, employees.reportsTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, photo, notes, reportsTo);
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeID=" + employeeID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", title='" + title + '\'' +
                ", titleOfCourtesy='" + titleOfCourtesy + '\'' +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", extension=" + extension +
                ", photo='" + photo + '\'' +
                ", notes='" + notes + '\'' +
                ", reportsTo=" + reportsTo +
                '}'+"\n";
    }

    // User Methods
}
