package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;

import com.mycompany.app.exceptions.InvalidDataException;
import com.mycompany.app.models.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testConverter() {

        String data = """
                Name,Department,Salary
                Alice Johnson,Engineering,95000.0
                Bob Smith,Marketing,-82000.50
                Charlie Brown,Design,78000.0""";

        List<Employee> employees = App.converter(data);
        assertEquals(3, employees.size());
    }

    @Test
    public void testMaxSalaryEmployee() {

        String data = """
                Name,Department,Salary
                Alice Johnson,Engineering,95000.0
                Bob Smith,Marketing,-82000.50
                Charlie Brown,Design,78000.0""";

        List<Employee> employees = App.converter(data);

        Optional<Employee> max = App.maxSalaryEmployee(employees);
        assertTrue(max.isPresent());
        assertEquals("Alice Johnson", max.get().getName());
    }

    @Test
    public void testNegativeSalaryEmployees() {

        String data = """
                Name,Department,Salary
                Alice Johnson,Engineering,95000.0
                Bob Smith,Marketing,-82000.50
                Charlie Brown,Design,78000.0""";

        List<Employee> employees = App.converter(data);
        assertThrows(InvalidDataException.class, () -> App.checkNegativeSalaryEmployees(employees));
    }
}
