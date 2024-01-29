package CompanyTools;

import ErrorHandling.CompanySystemException;
import Utils.FactoryUtils;
import cls.Employee;
import cls.Engineer;
import cls.Manager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static ErrorHandling.Error.*;

public class Company {
    // Fields
    private static Company company = null;
    private final LinkedList<Employee> employees;

    // Constructors
    private Company() {
        employees = FactoryUtils.initEmployees();
    }

    // Getters/Setters
    public List<Employee> getEmployees() {
        return employees;
    }
    public static Company getInstance() {
        // Create single instance
        if(company == null) {
            company = new Company();
        }
        return company;
    }

    // User Methods
    public void addEmployee(Employee employee) throws CompanySystemException {
        if(getEmployees().contains(employee)) {
            throw new CompanySystemException(EMPLOYEE_ALREADY_EXISTS.getMessage());
        }
        else {
            employees.add(employee);
            System.out.println("Employee " + employee.getName() + " was added successfully!");
        }
    }
    public void removeEmployee(Employee employee) throws CompanySystemException {
        if(!getEmployees().contains(employee)) {
            //System.out.println("Employee "+employee.getName()+" does not exist in company.");
            throw new CompanySystemException(EMPLOYEE_NOT_FOUND.getMessage());
        }
        else {
            employees.remove(employee);
            System.out.println("Employee "+employee.getName()+" was removed successfully!");
        }
    }
    public void display() {
        Collections.sort(employees);
        for(Employee employee: employees) {
            System.out.println(employee);
        }
    }
    public double getAverageSalary() throws CompanySystemException {
        if(getEmployees() == null) {
            throw new CompanySystemException(NULL_OR_EMPTY.getMessage());
        }
        else {
            Double sum = 0.0;
            for (Employee employee : employees) {
                sum += employee.getSalary();
            }
            return sum / employees.size();
        }
    }
    public double getManagementAverageSalary() throws CompanySystemException {
        if(getEmployees() == null) {
            throw new CompanySystemException(NULL_OR_EMPTY.getMessage());
        }
        else {
            Double sum = 0.0;
            int countManagers = 0;
            for (Employee employee : employees) {
                if (employee instanceof Manager) {
                    sum += employee.getSalary();
                    countManagers++;
                }
            }
            return sum / countManagers;
        }
    }
    public double getMonthlyPayment() throws CompanySystemException {
        if(getEmployees() == null) {
            throw new CompanySystemException(NULL_OR_EMPTY.getMessage());
        }
        else {
            Double sum = 0.0;
            for (Employee employee : employees) {
                sum += employee.getSalary();
            }
            return sum;
        }
    }
    public double getYearlyPayment() throws CompanySystemException {
        return getMonthlyPayment()*12;
    }
    public int getTotalNumOfEmployees() throws CompanySystemException {
        if(getEmployees() == null) {
            throw new CompanySystemException(NULL_OR_EMPTY.getMessage());
        }
        else {
            return employees.size();
        }
    }
    public int getTotalNumOfManagers() throws CompanySystemException {
        if(getEmployees() == null) {
            throw new CompanySystemException(NULL_OR_EMPTY.getMessage());
        }
        else {
            int countManagers = 0;
            for (Employee employee : employees) {
                if (employee instanceof Manager) {
                    countManagers++;
                }
            }
            return countManagers;
        }
    }
}
