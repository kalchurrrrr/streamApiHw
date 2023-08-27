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
        String key = generateKey(employee.getFirstName(), employee.getLastName());
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employeeMap.put(key, employee);
    }

    public void removeEmployee(Employee employee) {
        String key = generateKey(employee.getFirstName(), employee.getLastName());
        if (employeeMap.remove(key) == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
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

}