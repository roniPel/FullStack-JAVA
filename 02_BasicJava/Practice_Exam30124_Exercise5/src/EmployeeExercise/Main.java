package EmployeeExercise;

import EmployeeExercise.Company;
import EmployeeExercise.Employee;
import EmployeeExercise.FactoryUtils;
import EmployeeExercise.Helper;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = Company.getInstance();
//        // Exercise 4 - part 1
//        Thread raiseSal = new Thread(new SalaryUpdate());
//        raiseSal.start();
        //company.display();
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

        // Exercise 5
        // Store data
        try {
            company.storeEmployeeData();
        } catch (CompanySystemException e) {
            throw new RuntimeException(e);
        }
        // Load data
        List<Employee> employeeList = new LinkedList<>();
        try {
            employeeList = company.loadEmployeeData();
        } catch (CompanySystemException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The loaded employee list: ");
        for(Employee e:employeeList) {
            System.out.println(e);
        }
    }
}
