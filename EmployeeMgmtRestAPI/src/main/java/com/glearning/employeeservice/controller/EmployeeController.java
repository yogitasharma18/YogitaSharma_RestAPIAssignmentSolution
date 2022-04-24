package com.glearning.employeeservice.controller;

import com.glearning.employeeservice.model.Employee;
import com.glearning.employeeservice.service.EmployeeService;

import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Map<String, Object> fetchAllEmployees(
            @RequestParam(value="page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "property", required = false, defaultValue = "name") String property
    ){
        return this.employeeService.findAll(page, size, direction, property);
    }

    @GetMapping("/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") long empId){
        return this.employeeService.findEmployeeById(empId);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody @Valid Employee employee)
    {
        employee.getDependents().forEach(dependent -> dependent.setEmployee(employee));
        return this.employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") long empId) {
        this.employeeService.deleteEmployeeById(empId);
    }

    @GetMapping("/email/{email}")
    public Employee fetchByEmail(@PathVariable String email){
        return this.employeeService.fetchEmployeesByEmail(email);
    }
    @GetMapping("/name/{name}")
    public List<Employee> fetchByName(@PathVariable String name){
        return this.employeeService.fetchEmployeesByName(name);
    }
    @GetMapping("/salary")
    public Set<Employee> fetchEmployeeBySalary(
            @RequestParam("min") double min,
            @RequestParam("max") double max
            ){
        return this.employeeService.fetchEmployeesBySalary(min, max);
    }
}
