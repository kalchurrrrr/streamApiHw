package com.streamApiHw.streamApiHw;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(String departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().equals(departmentId))
                .collect(Collectors.toList());
    }

    public double getDepartmentSalarySum(String departmentId) {
        if (departmentId == null || departmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Необходимо предоставить идентификационный номер отдела");
        }
        List<Employee> employees = getEmployeesByDepartment(departmentId);
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }


    public Employee getEmployeeWithMaxSalary(String departmentId) {
        List<Employee> employees = getEmployeesByDepartment(departmentId);
        if (employees.isEmpty()) {
            throw new NoSuchElementException("В отделе нет сотрудников");
        }
        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    public Employee getEmployeeWithMinSalary(String departmentId) {
        List<Employee> employees = getEmployeesByDepartment(departmentId);
        if (employees.isEmpty()) {
            throw new NoSuchElementException("В отделе нет сотрудников");
        }
        return employees.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow();
    }

    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().hashCode()));
    }
}