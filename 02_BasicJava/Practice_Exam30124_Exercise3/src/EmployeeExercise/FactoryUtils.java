package EmployeeExercise;

import java.util.*;

public class FactoryUtils {
    static double randSalary(int min, int max) {
        max +=1;
        min += 1;
        double range = max - min;
        return (Math.random() * range) + min;
    }
    static double GenerateRandSalary(Employee e) {
        double salary = 0;
        if(e instanceof Secretary) {
            salary = randSalary(7000,8500);
        }
        else if(e instanceof Engineer) {
            salary = randSalary(9000,11000);
        }
        else if(e instanceof Manager) {
            if(e instanceof Director){
                salary = randSalary(18000,35000);
            }
            else
                salary = randSalary(12000,15500);
        }
        else
            salary = randSalary(6000,7000);
        return salary;
    }

    public static List<Employee> initEmployees() {
        //int size = 10;
        double salary = 0;
        Employee e1;
        List<Employee> employeeList = new LinkedList<>();
        // 2 - secretaries, 2 - managers, 3 - employees, 1 - director, 2 - engineers
        // Add secretaries
        for (int i = 0; i < 2; i++) {
            e1 = new Secretary(Name.getRandName(),0,"Entrance");
            salary = GenerateRandSalary(e1);
            e1.setSalary(salary);
            employeeList.add(e1);
        }
        // Add Managers
        String[] department = new String[] {"Product", "Programming"};
        for (int i = 0; i < 2; i++) {
            e1 = new Manager(Name.getRandName(),0,department[i]);
            salary = GenerateRandSalary(e1);
            e1.setSalary(salary);
            employeeList.add(e1);
        }
        /*// Add Employees
        for (int i = 0; i < 3; i++) {
            e1 = new Employee(Name.getRandName(),0);
            salary = GenerateRandSalary(e1);
            e1.setSalary(salary);
            employeeList.add(e1);
        }*/
        // Add director
        e1 = new Director(Name.getRandName(),0,"Product","Development");
        salary = GenerateRandSalary(e1);
        e1.setSalary(salary);
        employeeList.add(e1);
        // Add Engineers
        String[] speciality = new String[] {"FrontEnd", "BackEnd", "Web", "Apple", "Android"};
        for (int i = 0; i < 5; i++) {
            e1 = new Engineer(Name.getRandName(),0,speciality[i]);
            salary = GenerateRandSalary(e1);
            e1.setSalary(salary);
            employeeList.add(e1);
        }
        return employeeList;
    }
}
