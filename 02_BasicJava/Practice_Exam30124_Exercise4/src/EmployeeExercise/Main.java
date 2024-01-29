package EmployeeExercise;

import EmployeeExercise.Company;
import EmployeeExercise.Employee;
import EmployeeExercise.FactoryUtils;
import EmployeeExercise.Helper;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Company company = Company.getInstance();
//        // Exercise 4 - part 1
//        Thread raiseSal = new Thread(new SalaryUpdate());
//        raiseSal.start();
        company.display();
        Helper helper = new Helper();
        helper.report();

//        // Exercise 3
//        /*// Remove a non-existing worker
//        Director director = new Director("Randy",56000,"Makeup","Elite snobs");
//        company.removeEmployee(director);*/
//
//        /*// Try to create report with no workers in company
//        int size = company.getEmployees().size();
//        for (int i = 0; i < size; i++) {
//            company.removeEmployee(company.getEmployees().get(0));
//        }
//        helper.report();*/

//        // Exercise 4 - part 2
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        SalaryUpdate.shutdown();
    }
}
