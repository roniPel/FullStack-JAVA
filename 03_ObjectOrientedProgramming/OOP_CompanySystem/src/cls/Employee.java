package cls;

import Utils.FactoryUtils;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class Employee implements Comparable<Employee> {
    // Fields
    private String name;
    private Double salary;

    // Constructors
    public Employee() {
        this(Name.getRandName(),0.0);
    }
    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }


    // Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", salary=" + FactoryUtils.beautifySalary(salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }

    @Override
    public int compareTo(Employee other) {
        return this.getName().compareTo(other.getName());
    }

    // User Methods
    public abstract void draw();


}
