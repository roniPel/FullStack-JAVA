package EmployeeExercise;

public class Employee {
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private double salary;

    public boolean equals(Employee e1, Employee e2) {
        return (e1.name == e2.name) && (e1.salary == e2.salary);
    }

    @Override
    public String toString() {
        return "Name: "+getName()+", Salary: "+getSalary()+" NIS\n";
    }
}
