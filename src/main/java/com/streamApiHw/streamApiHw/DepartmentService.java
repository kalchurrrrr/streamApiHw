package com.streamApiHw.streamApiHw;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalaryInDepartment(String departmentId) {
        List<Employee> employeesInDepartment = employeeService.getEmployeesByDepartment(departmentId);
        return employeesInDepartment.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeWithMinSalaryInDepartment(String departmentId) {
        List<Employee> employeesInDepartment = employeeService.getEmployeesByDepartment(departmentId);
        return employeesInDepartment.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> getAllEmployeesInDepartment(String departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    public Map<String, List<Employee>> getAllEmployeesByDepartment() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
