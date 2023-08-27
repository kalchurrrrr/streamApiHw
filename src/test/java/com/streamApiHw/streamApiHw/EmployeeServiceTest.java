package com.streamApiHw.streamApiHw;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void testAddEmployee() {
        EmployeeService employeeService = mock(EmployeeService.class);
        Employee employee = new Employee("Александр", "Иванов", 5000, "Department_1");
        doReturn(true).when(employeeService).addEmployee(employee);
        assertTrue(employeeService.addEmployee(employee));
        verify(employeeService, times(1)).addEmployee(employee);
    }

    @Test
    void testRemoveEmployee() {
        EmployeeService employeeService = mock(EmployeeService.class);
        Employee employee = new Employee("Александр", "Иванов", 5000, "Department_1");
        doReturn(true).when(employeeService).removeEmployee(employee);
        assertTrue(employeeService.removeEmployee(employee));
        verify(employeeService, times(1)).removeEmployee(employee);
    }

    @Test
    void testFindEmployeeById() {
        EmployeeService employeeService = mock(EmployeeService.class);
        Employee employee = new Employee("Александр", "Иванов", 5000, "Department_1");
        doReturn(employee).when(employeeService).findEmployeeById(1);
        assertEquals(employee, employeeService.findEmployeeById(1));
        verify(employeeService, times(1)).findEmployeeById(1);
    }

    @Test
    void testFindAllEmployees() {
        EmployeeService employeeService = mock(EmployeeService.class);
        List<Employee> employees = Arrays.asList(
                new Employee("Александр", "Иванов", 5000, "Department_1"),
                new Employee("Иван", "Александров", 6000, "Department_1")
        );
        doReturn(employees).when(employeeService).findAllEmployees();
        assertEquals(employees, employeeService.findAllEmployees());
        verify(employeeService, times(1)).findAllEmployees();
    };
}