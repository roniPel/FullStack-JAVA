package EmployeeExercise;

public class SalaryUpdate implements Runnable {
    Company company = Company.getInstance();

    static volatile boolean shutdown = false;
    @Override
    public void run() {
        while(!shutdown && (company.getAverageSalary() <= 15000) ) {
            System.out.println("Current average salary: "+company.getAverageSalary());
            for (Employee e : company.employees) {
//                System.out.println(e.getName()+"'s salary: "+e.getSalary());
                e.setSalary(e.getSalary() * 1.02);
            }
            System.out.println("Updated average salary: "+company.getAverageSalary());
//            for (Employee e : company.employees) {
//                System.out.println(e.getName()+"'s salary: "+e.getSalary());
//            }
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(company.getAverageSalary() >= 15000) {
            System.out.println("NOW EVERYBODY HAPPY!");
        }
    }
    public static void shutdown() {
        shutdown = true;
    }
}
