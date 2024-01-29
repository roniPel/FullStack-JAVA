package EmployeeExercise;

import java.util.List;

public class Helper {
    static public double avgSalary(List<Employee> employees) {
        double totalSumSal = 0;
        for (Employee e: employees){
           totalSumSal += e.getSalary();
        }
        return totalSumSal/employees.size();
    }

    static public double avgManagementSalary(List<Employee> employees) {
        double totalSumSal = 0;
        int count = 0;
        for (Employee e: employees){
            if(e instanceof Manager) {
                totalSumSal += e.getSalary();
                count++;
            }
        }
        return totalSumSal/count;
    }
    static public void display(List<Employee> employees) {
        for(Employee e:employees) {
            System.out.println(e);
        }
    }
    static public void report(List<Employee> employees) {
        System.out.println("*** Employee List ***");
        display(employees);
        System.out.println();
        System.out.println("Average Salary: "+avgSalary(employees)+" NIS");
        System.out.println("Manager Average Salary: "+avgManagementSalary(employees)+" NIS");
    }
}
