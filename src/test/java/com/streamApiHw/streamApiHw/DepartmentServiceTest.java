package com.streamApiHw.streamApiHw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepartmentServiceTest {
    private DepartmentService departmentService;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentService = new DepartmentService(employeeService);
    }

    @Test
    public void testGetEmployeesByDepartment_ReturnsEmployees_WhenDepartmentExists() {
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(expectedEmployees);

        List<Employee> actualEmployees = departmentService.getEmployeesByDepartment("IT");

        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void testGetEmployeesByDepartment_ReturnsEmptyList_WhenDepartmentDoesNotExist() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        List<Employee> actualEmployees = departmentService.getEmployeesByDepartment("Finance");

        assertEquals(Collections.emptyList(), actualEmployees);
    }

    @Test
    public void testGetEmployeesByDepartment_ThrowsException_WhenNoDepartmentIdProvided() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        List<Employee> actualEmployees = departmentService.getEmployeesByDepartment(null);
        assertEquals(Collections.emptyList(), actualEmployees);

        actualEmployees = departmentService.getEmployeesByDepartment("");
        assertEquals(Collections.emptyList(), actualEmployees);

        actualEmployees = departmentService.getEmployeesByDepartment("   ");
        assertEquals(Collections.emptyList(), actualEmployees);
    }

    @Test
    public void testGetDepartmentSalarySum_ReturnsCorrectSum_WhenDepartmentExists() {
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        double expectedSalarySum = 11000;
        double actualSalarySum = departmentService.getDepartmentSalarySum("IT");

        assertEquals(expectedSalarySum, actualSalarySum);
    }

    @Test
    public void testGetDepartmentSalarySum_ReturnsZero_WhenDepartmentDoesNotExist() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        double actualSalarySum = departmentService.getDepartmentSalarySum("Finance");

        assertEquals(0, actualSalarySum);
    }

    @Test
    public void testGetDepartmentSalarySum_ThrowsException_WhenNoDepartmentIdProvided() {
        assertThrows(IllegalArgumentException.class,
                () -> departmentService.getDepartmentSalarySum(null));

        assertThrows(IllegalArgumentException.class,
                () -> departmentService.getDepartmentSalarySum(""));

        assertThrows(IllegalArgumentException.class,
                () -> departmentService.getDepartmentSalarySum("   "));
    }

    @Test
    public void testGetEmployeeWithMaxSalary_ReturnsEmployeeWithMaxSalary_WhenDepartmentExists() {
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT"),
                new Employee("Александр", "Пистолетов", 7000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        Employee expectedEmployee = new Employee("Александр", "Пистолетов", 7000, "IT");
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary("IT");

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testGetEmployeeWithMaxSalary_ThrowsException_WhenDepartmentDoesNotExist() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMaxSalary("Finance"));
    }

    @Test
    public void testGetEmployeeWithMaxSalary_ThrowsException_WhenNoEmployeesInDepartment() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMaxSalary("HR"));
    }

    @Test
    public void testGetEmployeeWithMaxSalary_ThrowsException_WhenNoDepartmentIdProvided() {
        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMaxSalary(null));

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMaxSalary(""));

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMaxSalary("   "));
    }

    @Test
    public void testGetEmployeeWithMinSalary_ReturnsEmployeeWithMinSalary_WhenDepartmentExists() {
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT"),
                new Employee("Александр", "Пистолетов", 4000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        Employee expectedEmployee = new Employee("Александр", "Пистолетов", 4000, "IT");
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary("IT");

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testGetEmployeeWithMinSalary_ThrowsException_WhenDepartmentDoesNotExist() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMinSalary("Finance"));
    }

    @Test
    public void testGetEmployeeWithMinSalary_ThrowsException_WhenNoEmployeesInDepartment() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "IT"),
                new Employee("Ольга", "Романова", 6000, "IT")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMinSalary("HR"));
    }

    @Test
    public void testGetEmployeeWithMinSalary_ThrowsException_WhenNoDepartmentIdProvided() {
        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMinSalary(null));

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMinSalary(""));

        assertThrows(NoSuchElementException.class,
                () -> departmentService.getEmployeeWithMinSalary("   "));
    }

    @Test
    public void testGetAllEmployeesByDepartment_ReturnsEmployeesGroupedByDepartment() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, "HR"),
                new Employee("Ольга", "Романова", 6000, "IT"),
                new Employee("Александр", "Пистолетов", 4000, "HR"),
                new Employee("Аркадий", "Паравозов", 8000, "IT"),
                new Employee("Владимир", "Централов", 5500, "HR")
        );
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        departmentService.getAllEmployeesByDepartment();

        verify(employeeService).getAllEmployees();
    }
}