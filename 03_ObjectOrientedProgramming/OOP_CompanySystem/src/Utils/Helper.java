package Utils;

import CompanyTools.Company;
import ErrorHandling.CompanySystemException;
import cls.Employee;
import cls.Manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import static jdk.internal.org.jline.keymap.KeyMap.display;

public class Helper {

    // User Methods
   /* public static Double avgSalary(List<Employee> employees) {
        Double sum = 0.0;
        for(Employee employee: employees) {
            sum += employee.getSalary();
        }
        return sum/employees.size();
    }
*/
    /*public static Double avgManagementSalary(List<Employee> employees) {
        Double sum = 0.0;
        int countManagers = 0;
        for(Employee employee: employees) {
            if(employee instanceof Manager) {
                sum += employee.getSalary();
                countManagers++;
            }
        }
        return sum/countManagers;
    }*/

    /*public static void display(List<Employee> employees) {
        System.out.println("*** The company employee list ***");
        for(Employee employee: employees) {
            System.out.println(employee);
        }
    }*/

    /*public static void report(List<Employee> employees) {
        System.out.println("===== COMPANY REPORT =====");
        display(employees);
        System.out.println();
        System.out.println("** Company Statistics **");
        System.out.println("Average salary: "+FactoryUtils.beautifySalary(avgSalary(employees)));
        System.out.println("Average Management salary: "+FactoryUtils.beautifySalary(avgManagementSalary(employees)));
    }*/

    public static void report() throws CompanySystemException {
        double salary = 0.0;
        System.out.println("===== COMPANY REPORT =====");
        System.out.println();
        System.out.println("** Company Statistics **");
        System.out.println("Number of employees: "+
                Company.getInstance().getTotalNumOfEmployees() );
        System.out.println("Number of managers: "+
                Company.getInstance().getTotalNumOfManagers());

        salary = Company.getInstance().getMonthlyPayment();
        System.out.println("Total monthly payment: "+ FactoryUtils.beautifySalary(salary) );
        salary = Company.getInstance().getYearlyPayment();
        System.out.println("Total yearly payment: "+ FactoryUtils.beautifySalary(salary) );
        salary = Company.getInstance().getAverageSalary();
        System.out.println("Average salary: "+ FactoryUtils.beautifySalary(salary) );
        salary = Company.getInstance().getManagementAverageSalary();
        System.out.println("Average Management salary: "+ FactoryUtils.beautifySalary(salary) );
    }

    public static String format(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        return time.format(formatter);
    }
}
