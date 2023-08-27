package com.streamApiHw.streamApiHw;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        List<Employee> allEmployees = employeeService.getAllEmployees();

        List<Employee> employeesByDepartment = allEmployees.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());

        return employeesByDepartment;
    }

    public BigDecimal getSalarySumByDepartmentId(int departmentId) {
        List<Employee> employees = getEmployeesByDepartmentId(departmentId);

        BigDecimal salarySum = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return salarySum;
    }

    public BigDecimal getMaxSalaryByDepartmentId(int departmentId) {
        List<Employee> employees = getEmployeesByDepartmentId(departmentId);

        if (employees.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal maxSalary = employees.stream()
                .map(Employee::getSalary)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return maxSalary;
    }

    public BigDecimal getMinSalaryByDepartmentId(int departmentId) {
        List<Employee> employees = getEmployeesByDepartmentId(departmentId);

        if (employees.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal minSalary = employees.stream()
                .map(Employee::getSalary)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return minSalary;
    }

    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        List<Employee> allEmployees = employeeService.getAllEmployees();

        Map<Integer, List<Employee>> employeesByDepartmentMap = allEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));

        return employeesByDepartmentMap;
    }
}