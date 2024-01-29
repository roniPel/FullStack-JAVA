package ExceptionsExercise;

public class Employee {
    private static final double MAXIMUM_SALARY = 40000;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        if (salary <= MAXIMUM_SALARY) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
        else
            throw new InvalidEmployeeSalaryException("The salary entered is above the maximum salary allowed!");
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary;
    }
}
