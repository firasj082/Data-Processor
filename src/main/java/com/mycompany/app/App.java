package com.mycompany.app;
import com.mycompany.app.models.Employee;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;
import com.mycompany.app.exceptions.InvalidDataException;

public class App {

    public static List<Employee> converter(String data){

        return new CsvToBeanBuilder<Employee>(new StringReader(data))
                .withType(Employee.class)
                .build()
                .parse();
    }

    public static Optional<Employee> maxSalaryEmployee(List<Employee> employees) {

        return employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
    }

    public static void checkNegativeSalaryEmployees(List<Employee> employees) {

        List<Employee> negs = employees.stream().filter(e -> e.getSalary() < 0).toList();
        if(!negs.isEmpty()){
            throw new InvalidDataException("Some Salaries Are Of Negative Values.");
        }
    }

    public static void main(String[] args) {

        String data = """
                Name,Department,Salary
                Alice Johnson,Engineering,95000.0
                Bob Smith,Marketing,-82000.50
                Charlie Brown,Design,78000.0""";

        List<Employee> employees = converter(data);

        double average = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        Optional<Employee> max = maxSalaryEmployee(employees);

        Map<String, List<Employee>> departments = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        max.ifPresent(e -> System.out.println(e.getName() + "   " + e.getSalary()));

    }
}
