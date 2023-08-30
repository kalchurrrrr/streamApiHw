package com.streamApiHw.streamApiHw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("id") String departmentId) {
        List<Employee> employees = departmentService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<Double> getDepartmentSalarySum(@PathVariable("id") String departmentId) {
        double salarySum = departmentService.getDepartmentSalarySum(departmentId);
        return ResponseEntity.ok(salarySum);
    }

    @GetMapping("/{id}/salary/max")
    public ResponseEntity<Employee> getEmployeeWithMaxSalary(@PathVariable("id") String departmentId) {
        Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}/salary/min")
    public ResponseEntity<Employee> getEmployeeWithMinSalary(@PathVariable("id") String departmentId) {
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<Map<Integer, List<Employee>>> getAllEmployeesByDepartment() {
        Map<Integer, List<Employee>> employeesByDepartment = departmentService.getAllEmployeesByDepartment();
        return ResponseEntity.ok(employeesByDepartment);
    }
}