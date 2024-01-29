package EmployeeExercise;

import java.util.List;

import static java.util.Collections.sort;

public class Company {
    private static Company company = new Company();
    final List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }
    private Company() {
        employees = FactoryUtils.initEmployees();
    }
    public static Company getInstance() {
        return company;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    public void display() {
        System.out.println("*** Employees ***");
        System.out.println();
        sort(getEmployees());
        for (Employee e: getEmployees()) {
            System.out.println(e);
            e.draw();
        }
    }
    public double getAverageSalary() {
        double totalSumSal = 0;
        for (Employee e: employees) {
            totalSumSal += e.getSalary();
        }
        return totalSumSal/employees.size();
    }
    public double getManagementAverageSalary() {
        double totalSumSal = 0;
        int count = 0;
        for (Employee e: employees) {
            if(e instanceof Manager) {
                totalSumSal += e.getSalary();
                count++;
            }
        }
        return totalSumSal/count;
    }
    public double getMonthlyPayment() {
        double totalSumSal = 0;
        for (Employee e: employees) {
            totalSumSal += e.getSalary();
        }
        return totalSumSal;
    }
    public double getYearlyPayment() {
        double totalSumSal = 0;
        for (Employee e: employees) {
            totalSumSal += e.getSalary();
        }
        return totalSumSal*12;
    }
    public int getTotalNumOfManagers() {
        int count = 0;
        for (Employee e: employees) {
            if(e instanceof Manager) {
                count++;
            }
        }
        return count;
    }
    public int getTotalNumOfEmployees() {
        return employees.size();
    }
}
