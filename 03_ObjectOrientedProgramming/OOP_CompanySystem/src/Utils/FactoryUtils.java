package Utils;
import cls.*;

import java.text.DecimalFormat;
import java.util.*;

import static java.util.Map.entry;

public class FactoryUtils {

    private static final Map<String, Double[]> salaryLimits = Map.ofEntries(
            entry( "Employee", new Double[]{5000.0, 7000.0}),
            entry( "Secretary", new Double[]{7000.0, 8500.0}),
            entry( "Engineer", new Double[]{9000.0, 11000.0}),
            entry( "Manager", new Double[]{12000.0, 15500.0}),
            entry( "Director", new Double[]{18000.0, 35000.0})
            );

    // User Methods
    public static Double randSalary(Employee employee) {
        double min, max;
        //String className = employee.getClass().getSimpleName();
        min = salaryLimits.get(employee.getClass().getSimpleName())[0];
        max = salaryLimits.get(employee.getClass().getSimpleName())[1];
        return Math.random()*(max-min)+min;
    }

    public static LinkedList<Employee> initEmployees() {
        LinkedList<Employee> employeeList = new LinkedList<>();
        HashMap<String, Integer> types = createTypesConfiguration();
        for (String key: types.keySet()){
            // Create instances based on the hashmap types
            for (int i = 0; i < (types.get(key)); i++) {
                Employee employee = createInstance(key);
                Double salary = randSalary(employee);
                employee.setSalary(salary);
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    public static Set<Employee> addAll() {
        Set<Employee> employeeSet = new HashSet<>();
        List<Employee> employeeList = initEmployees();
        employeeSet.addAll(employeeList);
        return employeeSet;
    }
    private static Employee createInstance(String type) {
        return switch (type) {
            case "Secretary" -> new Secretary();
            case "Manager" -> new Manager();
            case "Director" -> new Director();
            case "Engineer" -> new Engineer();
            default -> new Engineer();
        };
    }
    private static HashMap<String, Integer> createTypesConfiguration() {
        //Create hash map of number of each type to create
        HashMap<String, Integer> types = new HashMap<>();
        types.put("Secretary",2);
        types.put("Manager",2);
        //types.put("Employee",3);
        types.put("Director",1);
        types.put("Engineer",5);
        return types;
    }

    public static String beautifySalary(Double salary) {
        String pattern = "###,###.##";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(salary);
    }

}
