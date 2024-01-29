package EmployeeExercise;
import java.io.*;
import java.util.List;
import static java.util.Collections.sort;

public class Company {
    //private static Company company = new Company();
    final List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }
    private static class MyWrapper {
        static Company company;

        static {
            try {
                company = new Company();
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Company getInstance() {
        return MyWrapper.company;
    }
//    public static Company getInstance() {
//        return company;
//    }
    private Company() throws CompanySystemException {
        //loadEmployeeData();
        employees = FactoryUtils.initEmployees();
//        Thread raiseSal = new Thread(new SalaryUpdate());
//        raiseSal.start();
    }

//    public void loadEmployeeData() throws CompanySystemException {
//        try {
//            String path = "emp.data";
//            if(!new File(path).exists()) {
//                File file = new File(path);
//            }
//            FileInputStream fis = new FileInputStream(path);
//            ObjectInputStream reader = new ObjectInputStream(fis);
//            employees = (List<Employee>) reader.readObject();
//            reader.close();
//            fis.close();
//        } catch (Exception e) {
//            throw new CompanySystemException(Error.FILE_READ_PROBLEM.getMessage());
//        }
//    }
public List<Employee> loadEmployeeData() throws CompanySystemException {
    List<Employee> employeeList;
        try {
        String path = "emp.data";
        if(!new File(path).exists()) {
            File file = new File(path);
        }
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream reader = new ObjectInputStream(fis);
        employeeList = (List<Employee>) reader.readObject();
        reader.close();
        fis.close();
    } catch (Exception e) {
        throw new CompanySystemException(Error.FILE_READ_PROBLEM.getMessage());
    }
        return employeeList;
}
    public void storeEmployeeData() throws CompanySystemException {
        try {
            FileOutputStream fos = new FileOutputStream("emp.data");
            ObjectOutputStream writer = new ObjectOutputStream(fos);
            writer.writeObject(Company.getInstance().getEmployees());
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new CompanySystemException(Error.FILE_WRITE_PROBLEM.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            try {
                throw (new CompanySystemException(Error.EMPLOYEE_ALREADY_EXIST.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else
            employees.add(employee);
    }
    public void removeEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            try {
                throw (new CompanySystemException(Error.EMPLOYEE_NOT_FOUND.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else
            employees.remove(employee);
    }
    public void display() {
        System.out.println("*** Employees ***");
        System.out.println();
        sort(getEmployees());
        for (Employee e: getEmployees()) {
            System.out.println(e);
            e.draw();
        }
    }
    public double getAverageSalary() {
        if (employees.isEmpty()) {
            try {
                throw(new CompanySystemException(Error.NULL_OR_EMPTY.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            double totalSumSal = 0;
            for (Employee e: employees) {
                totalSumSal += e.getSalary();
            }
            return totalSumSal/employees.size();
        }
    }
    public double getManagementAverageSalary() {
        if (employees.isEmpty()) {
            try {
                throw(new CompanySystemException(Error.NULL_OR_EMPTY.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            double totalSumSal = 0;
            int count = 0;
            for (Employee e: employees) {
                if(e instanceof Manager) {
                    totalSumSal += e.getSalary();
                    count++;
                }
            }
            return totalSumSal/count;
        }
    }
    public double getMonthlyPayment() {
        if (employees.isEmpty()) {
            try {
                throw(new CompanySystemException(Error.NULL_OR_EMPTY.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            double totalSumSal = 0;
            for (Employee e: employees) {
                totalSumSal += e.getSalary();
            }
            return totalSumSal;
        }
    }
    public double getYearlyPayment() {
        if (employees.isEmpty()) {
            try {
                throw(new CompanySystemException(Error.NULL_OR_EMPTY.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            double totalSumSal = 0;
            for (Employee e: employees) {
                totalSumSal += e.getSalary();
            }
            return totalSumSal*12;
        }
    }
    public int getTotalNumOfManagers() {
        if (employees.isEmpty()) {
            try {
                throw(new CompanySystemException(Error.NULL_OR_EMPTY.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            int count = 0;
            for (Employee e: employees) {
                if(e instanceof Manager) {
                    count++;
                }
            }
            return count;
        }
    }
    public int getTotalNumOfEmployees() {
        if (employees.isEmpty()) {
            try {
                throw(new CompanySystemException(Error.NULL_OR_EMPTY.getMessage()));
            } catch (CompanySystemException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return employees.size();
        }
    }
}
