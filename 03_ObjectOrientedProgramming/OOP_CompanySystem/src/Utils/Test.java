package Utils;
import CompanyTools.Company;
import ErrorHandling.CompanySystemException;
import cls.Employee;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        /*List<Employee> employeeList = FactoryUtils.initEmployees();
        Set<Employee> employees = FactoryUtils.addAll();
        System.out.println(employees);
        System.out.println();
        Helper.report(employeeList);*/

        Company company = Company.getInstance();
        company.display();
        try {
            Helper.report();
        } catch (CompanySystemException e) {
            throw new RuntimeException(e);
        }
    }
}
