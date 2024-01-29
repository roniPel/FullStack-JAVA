package EmployeeExercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {
    public void run() {
        Queue<Employee> employees = new LinkedList<Employee>();
        employees = FactoryUtils.initEmployees();
        Helper.display((List<Employee>) employees);
        Helper.report((List<Employee>) employees);
    }
}
