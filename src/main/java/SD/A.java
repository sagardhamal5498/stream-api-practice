package SD;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice Smith", 30, "alice.smith@example.com", "Manager", 80000.0, "HR", LocalDate.of(2020, 1, 15), "123-456-7890", "123 Maple St"));
        employees.add(new Employee(2, "Bob Johnson", 28, "bob.johnson@example.com", "Developer", 75000.0, "IT", LocalDate.of(2021, 3, 20), "234-567-8901", "456 Oak St"));
        employees.add(new Employee(3, "Carol Williams", 35, "carol.williams@example.com", "Designer", 70000.0, "Marketing", LocalDate.of(2019, 5, 10), "345-678-9012", "789 Pine St"));
        employees.add(new Employee(4, "David Brown", 32, "david.brown@example.com", "Analyst", 68000.0, "Finance", LocalDate.of(2018, 6, 22), "456-789-0123", "101 Elm St"));
        employees.add(new Employee(5, "Eva Davis", 29, "eva.davis@example.com", "Support", 60000.0, "Customer Service", LocalDate.of(2021, 8, 19), "567-890-1234", "202 Cedar St"));
        employees.add(new Employee(6, "Frank Miller", 40, "frank.miller@example.com", "Architect", 90000.0, "IT", LocalDate.of(2015, 9, 17), "678-901-2345", "303 Spruce St"));
        employees.add(new Employee(7, "Grace Wilson", 27, "grace.wilson@example.com", "Intern", 45000.0, "HR", LocalDate.of(2023, 1, 5), "789-012-3456", "404 Birch St"));
        employees.add(new Employee(8, "Hank Moore", 33, "hank.moore@example.com", "Consultant", 85000.0, "Consulting", LocalDate.of(2017, 11, 23), "890-123-4567", "505 Willow St"));
        employees.add(new Employee(9, "Ivy Taylor", 26, "", "Tester", 55000.0, "QA", LocalDate.of(2022, 2, 14), "901-234-5678", "606 Poplar St"));
        employees.add(new Employee(10, "Jack Anderson", 31, "jack.anderson@example.com", "Engineer", 78000.0, "Engineering", LocalDate.of(2019, 7, 30), "012-345-6789", "707 Walnut St"));



        /*
        Find the average salary of employees in the "IT" department.
         */

        OptionalDouble it = employees.stream().filter(x -> x.getDepartment().equalsIgnoreCase("IT"))
                .mapToDouble(Employee::getSalary).average();
        System.out.println(it.getAsDouble());

        /*
        List the names of employees who joined after January 1, 2020, sorted by their date of joining.
         */
        List<String> collect = employees.stream().filter(x -> x.getDateOfJoining().isAfter(LocalDate.of(2020, 01, 01)))
                .sorted(Comparator.comparing(Employee::getDateOfJoining)).map(Employee::getName).collect(Collectors.toList());
        System.out.println(collect);

        /*
        Find the highest paid employee in the "Engineering" department.
         */
        Optional<Employee> engineer = employees.stream().filter(x -> x.getDepartment().equalsIgnoreCase("Engineering"))
                .sorted(Comparator.comparing(Employee::getSalary).reversed()).findFirst();
        System.out.println(engineer.get());

        /*
        Group employees by their department and list the count of employees in each department.
         */
        Map<String, Long> collect1 = employees.stream().collect(Collectors.groupingBy(c -> c.getDepartment(), Collectors.counting()));
        System.out.println(collect1);

        /*
        Check if all employees have an email address.
        */
        boolean b = employees.stream().allMatch(x -> x.getEmail() != null && !x.getEmail().isEmpty());
        System.out.println(b);

        /*
        Find the employee with the longest tenure (the earliest date of joining).
         */
        Optional<Employee> min = employees.stream().min(Comparator.comparing(Employee::getDateOfJoining));
        System.out.println(min.get());

        /*
             Find the employee with the longest tenure (the earliest date of joining) with duration.
         */
        Optional<Period> period = employees.stream().min(Comparator.comparing(Employee::getDateOfJoining)).map(employee ->
                Period.between(employee.getDateOfJoining(), LocalDate.now()));
        System.out.println(period.get().getYears());

        employees.stream()
                .min(Comparator.comparing(Employee::getDateOfJoining))
                .ifPresent(employee -> {
                    Period tenure = Period.between(employee.getDateOfJoining(), LocalDate.now());
                    System.out.println("Employee with the longest tenure: " + employee.getName());
                    System.out.println("Tenure duration: " + tenure.getYears() + " years, "
                            + tenure.getMonths() + " months, "
                            + tenure.getDays() + " days.");
                });

        /*
        Partition employees into two lists: those who are older than 30 and those who are 30 or younger.
         */
        Map<Boolean, List<Employee>> collect2 = employees.stream().collect(Collectors.partitioningBy(x -> x.getAge() <= 30));
        System.out.println(collect2);

        /*
        Create a map where the key is the department and the value is a list of employee names in that department.
         */
        final Map<String, List<String>> collect3 = employees.stream().collect(Collectors.groupingBy(x -> x.getDepartment(),
                Collectors.mapping(x -> x.getName(), Collectors.toList())));
        System.out.println(collect3);

        /*
        Find the average age of employees in each department.
         */

        Map<String, Double> collect4 = employees.stream().collect(Collectors.groupingBy(x -> x.getDepartment(), Collectors.averagingDouble(Employee::getAge)));
        System.out.println(collect4);

        /*
        Get a list of employees whose names start with a specific letter, sorted by their salary in descending order.
         */
        char startingLetter = 'A';
        List<Employee> a = employees.stream().filter(x -> x.getName().startsWith("A"))
                .sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println(a);

        /*
        Find the total number of employees who joined in a given year.
         */
        long count = employees.stream().filter(x -> x.getDateOfJoining().getYear() == 2021).count();
        System.out.println(count);

        /*
        Find the employee with the highest and lowest salary in each department.
         */
        Map<String, Optional<Employee>> collect5 = employees.stream().collect(Collectors.groupingBy(x -> x.getDepartment(), Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(collect5);

        /*
        Find the department with the highest average salary.
         */

        final String ans1001 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No Department");
        System.out.println(ans1001);

    }

    }
