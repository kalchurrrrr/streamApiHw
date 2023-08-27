package com.streamApiHw.streamApiHw;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private Map<String, Employee> employeeMap;
    private final int maxEmployeesCount;

    public EmployeeService() {
        employeeMap = new HashMap<>();
        maxEmployeesCount = 10;
    }
    public void addEmployee(Employee employee) {
        if (employeeMap.size() >= maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников");
        }
        int id = employee.getEmployeeId(); // Используем employeeId вместо имени и фамилии для генерации ключа
        if (employeeMap.containsKey(id)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employeeMap.put(id, employee); // Используем employeeId как ключ
    }

    public void removeEmployee(Employee employee) {
        String key = generateKey(employee.getFirstName(), employee.getLastName());
        if (!employeeMap.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employeeMap.remove(key);
    }

    public Employee findEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);
        return employeeMap.get(key);
    }

    public List<Employee> getAllEmployees() {
        return employeeMap.values().stream().collect(Collectors.toList());
    }

    private String generateKey(String firstName, String lastName) {
        return firstName.toLowerCase() + "_" + lastName.toLowerCase();
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeMap.values().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public List<Employee> findAllEmployees() {
        return null;
    }
    public Employee findEmployeeById(int employeeId) {
        List<Employee> allEmployees = getAllEmployees();

        for (Employee employee : allEmployees) {
            if (employee.getEmployeeId() == employeeId) { // замените firstName и lastName на employeeId
                return employee;
            }
        }

        return null;
    }
}