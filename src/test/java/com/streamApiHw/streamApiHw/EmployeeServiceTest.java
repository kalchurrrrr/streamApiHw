package com.streamApiHw.streamApiHw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    public void testAddEmployee() {
        // Создаем нового сотрудника
        Employee employee = new Employee("Иван", "Иванов", 5000, "IT");

        // Добавляем сотрудника
        employeeService.addEmployee(employee);

        // Проверяем, что сотрудник успешно добавлен
        assertTrue(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    public void testAddEmployeeThrowsExceptionWhenStorageIsFull() {
        // Создаем 10 сотрудников, чтобы заполнить хранилище
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee("Employee" + i, "Lastname" + i, 1000 * i, "Department" + i);
            employeeService.addEmployee(employee);
        }

        // Создаем нового сотрудника, для которого не хватит места в хранилище
        Employee employee = new Employee("Иван", "Иванов", 5000, "IT");

        // Проверяем, что при добавлении нового сотрудника выбрасывается исключение EmployeeStorageIsFullException
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void testAddEmployeeThrowsExceptionWhenEmployeeAlreadyExists() {
        // Создаем нового сотрудника
        Employee employee = new Employee("Иван", "Иванов", 5000, "IT");

        // Добавляем сотрудника
        employeeService.addEmployee(employee);

        // Повторно добавляем того же сотрудника
        // Проверяем, что при повторном добавлении сотрудника выбрасывается исключение EmployeeAlreadyAddedException
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void testRemoveEmployee() {
        // Создаем нового сотрудника
        Employee employee = new Employee("Иван", "Иванов", 5000, "IT");

        // Добавляем сотрудника
        employeeService.addEmployee(employee);

        // Удаляем сотрудника
        employeeService.removeEmployee(employee);

        // Проверяем, что сотрудник успешно удален
        assertFalse(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    public void testRemoveEmployeeThrowsExceptionWhenEmployeeNotFound() {
        // Создаем нового сотрудника
        Employee employee = new Employee("Иван", "Иванов", 5000, "IT");

        // Пытаемся удалить сотрудника, которого нет в хранилище
        // Проверяем, что при удалении несуществующего сотрудника выбрасывается исключение EmployeeNotFoundException
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(employee));
    }

    @Test
    public void testFindEmployee() {
        // Создаем нового сотрудника
        Employee employee = new Employee("Иван", "Иванов", 5000, "IT");

        // Добавляем сотрудника
        employeeService.addEmployee(employee);

        // Ищем сотрудника по имени и фамилии
        Employee foundEmployee = employeeService.findEmployee("Иван", "Иванов");

        // Проверяем, что найденный сотрудник соответствует добавленному сотруднику
        assertEquals(employee, foundEmployee);
    }

    @Test
    public void testFindEmployeeReturnsNullWhenEmployeeNotFound() {
        // Ищем несуществующего сотрудника
        // Проверяем, что при поиске несуществующего сотрудника метод возвращает null
        assertNull(employeeService.findEmployee("Иван", "Иванов"));
    }
}