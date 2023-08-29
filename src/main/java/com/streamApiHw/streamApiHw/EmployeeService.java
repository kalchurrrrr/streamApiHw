package com.streamApiHw.streamApiHw;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees;
    private static final int MAX_EMPLOYEES = 10;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public void addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }

        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников");
        }

        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        employees.remove(employee);
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        return null;
    }
}