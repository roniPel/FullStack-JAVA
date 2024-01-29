package EmployeeExercise;

import java.time.LocalDateTime;
import java.util.List;

public class Helper {
//    static public double avgSalary(List<Employee> employees) {
//        double totalSumSal = 0;
//        for (Employee e: employees){
//           totalSumSal += e.getSalary();
//        }
//        return totalSumSal/employees.size();
//    }

//    static public double avgManagementSalary(List<Employee> employees) {
//        double totalSumSal = 0;
//        int count = 0;
//        for (Employee e: employees){
//            if(e instanceof Manager) {
//                totalSumSal += e.getSalary();
//                count++;
//            }
//        }
//        return totalSumSal/count;
//    }
//    static public void display(List<Employee> employees) {
//        for(Employee e:employees) {
//            System.out.println(e);
//        }
//    }
    public void report() {
        Company company = Company.getInstance();
        try {
            System.out.println(" *** Company Report ***");
            System.out.println("Number of employees: "+company.getTotalNumOfEmployees());
            System.out.println("Number of managers: "+company.getTotalNumOfManagers());
            System.out.println("Total monthly payment: "+company.getMonthlyPayment()+" NIS");
            System.out.println("Total yearly payment: "+company.getYearlyPayment()+" NIS");
            System.out.println("Average Salary: "+Company.getInstance().getAverageSalary()+" NIS");
            System.out.println("Manager Average Salary: "+Company.getInstance().getManagementAverageSalary()+" NIS");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static String format(LocalDateTime time) {
        return time.getDayOfMonth()+"/"+time.getMonthValue()+"/"+time.getYear()+"-"+
                String.format("%02d:%02d:%02d",time.getHour(),time.getMinute(),time.getSecond());
    }
}
