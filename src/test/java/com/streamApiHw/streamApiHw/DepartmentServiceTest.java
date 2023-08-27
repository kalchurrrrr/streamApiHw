package com.streamApiHw.streamApiHw;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {
    @Test
    void testGetEmployeesByDepartmentId() {
        // Создание мок-объекта EmployeeService
        EmployeeService employeeService = mock(EmployeeService.class);

        // Создание тестовых данных
        List<Employee> employees = Arrays.asList(
                new Employee("Александр", "Иванов", 5000, "Department_1"),
                new Employee("Иван", "Александров", 6000, "Department_1"),
                new Employee("Ольга", "Романова", 7000, "Department_2")
        );

        // Установка поведения мок-объекта
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Создание объекта DepartmentService с зависимостью на мок-объект EmployeeService
        DepartmentService departmentService = new DepartmentService(employeeService);

        // Вызов метода и проверка результата
        List<Employee> employeesByDepartment = departmentService.getEmployeesByDepartmentId(1);
        assertEquals(2, employeesByDepartment.size());
        assertTrue(employeesByDepartment.contains(employees.get(0)));
        assertTrue(employeesByDepartment.contains(employees.get(1)));

        // Проверка, что метод getAllEmployees() был вызван один раз
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetSalarySumByDepartmentId() {
        // Создание мок-объекта EmployeeService
        EmployeeService employeeService = mock(EmployeeService.class);

        // Создание тестовых данных
        List<Employee> employees = Arrays.asList(
                new Employee("Александр", "Иванов", 5000, "Department_1"),
                new Employee("Иван", "Александров", 6000, "Department_1"),
                new Employee("Ольга", "Романова", 7000, "Department_2")
        );

        // Установка поведения мок-объекта
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Создание объекта DepartmentService с зависимостью на мок-объект EmployeeService
        DepartmentService departmentService = new DepartmentService(employeeService);

        // Вызов метода и проверка результата
        BigDecimal salarySum = departmentService.getSalarySumByDepartmentId(1);
        assertEquals(new BigDecimal("11000"), salarySum);

        // Проверка, что метод getAllEmployees() был вызван один раз
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetMaxSalaryByDepartmentId() {
        // Создание мок-объекта EmployeeService
        EmployeeService employeeService = mock(EmployeeService.class);

        // Создание тестовых данных
        List<Employee> employees = Arrays.asList(
                new Employee("Александр", "Иванов", 5000, "Department_1"),
                new Employee("Иван", "Александров", 6000, "Department_1"),
                new Employee("Ольга", "Романова", 7000, "Department_2")
        );

        // Установка поведения мок-объекта
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Создание объекта DepartmentService с зависимостью на мок-объект EmployeeService
        DepartmentService departmentService = new DepartmentService(employeeService);

        // Вызов метода и проверка результата
        BigDecimal maxSalary = departmentService.getMaxSalaryByDepartmentId(1);
        assertEquals(new BigDecimal("6000"), maxSalary);

        // Проверка, что метод getAllEmployees() был вызван один раз
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetMinSalaryByDepartmentId() {
        // Создание мок-объекта EmployeeService
        EmployeeService employeeService = mock(EmployeeService.class);

        // Создание тестовых данных
        List<Employee> employees = Arrays.asList(
                new Employee("Александр", "Иванов", 5000, "Department_1"),
                new Employee("Иван", "Александров", 6000, "Department_1"),
                new Employee("Ольга", "Романова", 7000, "Department_2")
        );

        // Установка поведения мок-объекта
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Создание объекта DepartmentService с зависимостью на мок-объект EmployeeService
        DepartmentService departmentService = new DepartmentService(employeeService);

        // Вызов метода и проверка результата
        BigDecimal minSalary = departmentService.getMinSalaryByDepartmentId(1);
        assertEquals(new BigDecimal("5000"), minSalary);

        // Проверка, что метод getAllEmployees() был вызван один раз
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetAllEmployeesByDepartment() {
        // Создание мок-объекта EmployeeService
        EmployeeService employeeService = mock(EmployeeService.class);

        // Создание тестовых данных
        List<Employee> employees = Arrays.asList(
                new Employee("Александр", "Иванов", 5000, "Department_1"),
                new Employee("Иван", "Александров", 6000, "Department_1"),
                new Employee("Ольга", "Романова", 7000, "Department_2")
        );

        // Установка поведения мок-объекта
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Создание объекта DepartmentService с зависимостью на мок-объект EmployeeService
        DepartmentService departmentService = new DepartmentService(employeeService);

        // Вызов метода и проверка результата
        Map<Integer, List<Employee>> employeesByDepartmentMap =
                departmentService.getAllEmployeesByDepartment();
        assertEquals(2, employeesByDepartmentMap.size());
        assertEquals(Arrays.asList(employees.get(0), employees.get(1)),
                employeesByDepartmentMap.get(1));
        assertEquals(Arrays.asList(employees.get(2)),
                employeesByDepartmentMap.get(2));

        // Проверка, что метод getAllEmployees() был вызван один раз
        verify(employeeService, times(1)).getAllEmployees();
    }
}