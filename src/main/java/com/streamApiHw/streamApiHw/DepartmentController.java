package com.streamApiHw.streamApiHw;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable int id) {
        List<Employee> employees = departmentService.getEmployeesByDepartmentId(id);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/department/{id}/salary/sum")
    public ResponseEntity<BigDecimal> getSalarySumByDepartmentId(@PathVariable int id) {
        BigDecimal salarySum = departmentService.getSalarySumByDepartmentId(id);
        return ResponseEntity.ok(salarySum);
    }

    @GetMapping("/department/{id}/salary/max")
    public ResponseEntity<BigDecimal> getMaxSalaryByDepartmentId(@PathVariable int id) {
        BigDecimal maxSalary = departmentService.getMaxSalaryByDepartmentId(id);
        return ResponseEntity.ok(maxSalary);
    }

    @GetMapping("/department/{id}/salary/min")
    public ResponseEntity<BigDecimal> getMinSalaryByDepartmentId(@PathVariable int id) {
        BigDecimal minSalary = departmentService.getMinSalaryByDepartmentId(id);
        return ResponseEntity.ok(minSalary);
    }

    @GetMapping("/department/employees")
    public ResponseEntity<Map<Integer, List<Employee>>> getAllEmployeesByDepartment() {
        Map<Integer, List<Employee>> employeesByDepartment = departmentService.getAllEmployeesByDepartment();
        return ResponseEntity.ok(employeesByDepartment);
    }
}