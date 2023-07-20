package com.streamApiHw.streamApiHw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam String departmentId) {
        return departmentService.getEmployeeWithMaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam String departmentId) {
        return departmentService.getEmployeeWithMinSalaryInDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployeesInDepartment(@RequestParam String departmentId) {
        return departmentService.getAllEmployeesInDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<String, List<Employee>> getAllEmployeesByDepartment() {
        return departmentService.getAllEmployeesByDepartment();
    }
}
