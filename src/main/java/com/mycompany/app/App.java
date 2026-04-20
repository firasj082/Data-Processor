package com.mycompany.app;
import com.mycompany.app.models.Employee;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.StringReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static List<Employee> converter(String data){

        return new CsvToBeanBuilder<Employee>(new StringReader(data))
                .withType(Employee.class)
                .build()
                .parse();
    }

    public static void main(String[] args) {

        String data = "Name,Department,Salary\n" +
                "Alice Johnson,Engineering,95000.0\n" +
                "Bob Smith,Marketing,82000.50\n" +
                "Charlie Brown,Design,78000.0";

        List<Employee> employees = converter(data);

        double average = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        Employee max = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        Map<String, List<Employee>> departments = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }
}
