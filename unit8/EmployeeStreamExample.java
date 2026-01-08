import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeStreamExample {

    public static void main(String[] args) {

        // Step 1: Read dataset and store it in a collection
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 28, "Engineering", 75000),
                new Employee("Bob", 35, "Finance", 85000),
                new Employee("Charlie", 40, "HR", 65000),
                new Employee("Diana", 32, "Engineering", 90000),
                new Employee("Ethan", 25, "Marketing", 60000)
        );

        // Step 2: Function interface to transform Employee into "Name - Department"
        Function<Employee, String> nameDepartmentFunction =
                employee -> employee.getName() + " - " + employee.getDepartment();

        // Step 3 & 5: Filter employees older than 30 and generate a new collection
        List<String> employeeInfoOver30 = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .map(nameDepartmentFunction)
                .collect(Collectors.toList());

        // Step 4: Calculate average salary using streams
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        // Output results
        System.out.println("Employees older than 30 (Name - Department):");
        employeeInfoOver30.forEach(System.out::println);

        System.out.println("\nAverage Salary of All Employees: $" + averageSalary);
    }
}
