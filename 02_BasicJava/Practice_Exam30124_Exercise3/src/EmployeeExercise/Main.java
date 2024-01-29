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
        /*// Remove a non-existing worker
        Director director = new Director("Randy",56000,"Makeup","Elite snobs");
        company.removeEmployee(director);*/

        // Try to create report with no workers in company
        int size = company.getEmployees().size();
        for (int i = 0; i < size; i++) {
            company.removeEmployee(company.getEmployees().get(0));
        }
        helper.report();
    }
}
