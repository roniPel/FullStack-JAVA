package EmployeeExercise;

import EmployeeExercise.Company;
import EmployeeExercise.Employee;
import EmployeeExercise.FactoryUtils;
import EmployeeExercise.Helper;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Company company = Company.getInstance();
        company.display();
        Helper helper = new Helper();
        helper.report();

        // Remove 5 workers
        for (int i = 0; i < 5; i++) {
            company.removeEmployee(company.getEmployees().get(0));
        }
        System.out.println("After removing 5 employees, the report is as follows: ");
        helper.report();

        // Add 3 secretaries
        String[] names = new String[] {"Martha","Yolanda","Babette"};
        String[] offices = new String[] {"Corner Office","Top Floor Office","Balcony Office"};
        Secretary temp;
        for (int i = 0; i < names.length; i++) {
            temp = new Secretary(names[i],0,offices[i]);
            temp.setSalary(FactoryUtils.GenerateRandSalary(temp));
            company.addEmployee(temp);
        }
        System.out.println("After adding 3 secretaries, the report is as follows: ");
        helper.report();
    }
}
