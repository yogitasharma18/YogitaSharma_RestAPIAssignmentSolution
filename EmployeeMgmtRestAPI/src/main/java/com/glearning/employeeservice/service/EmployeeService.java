package com.glearning.employeeservice.service;

import com.glearning.employeeservice.model.Employee;
import com.glearning.employeeservice.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //perform the business validation
    public Employee save(Employee employee){
        return this.employeeRepository.save(employee);
    }

    public Map<String, Object> findAll(int page, int size, String direction, String property) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest pageRequest = PageRequest.of(page, size, sortDirection, property);
        Page<Employee> employees = this.employeeRepository.findAll(pageRequest);
        long totalElements = employees.getTotalElements();
        int totalPages = employees.getTotalPages();
        List<Employee> empList = employees.getContent();
        int currentPage = employees.getNumber();

        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("page-no", currentPage);
        map.put("total-elements", totalElements);
        map.put("total-pages", totalPages);
        map.put("data", empList);
        return map;
    }

    public Employee findEmployeeById(long employeeId){
        //1. imperative

        /*if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        } else {
            throw new IllegalArgumentException("Employee with the given employee id does not exists");
        }*/

        //declarative
        /*Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
        return optionalEmployee.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id"));*/

        //one-liner
        return this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id"));
    }

    public Employee fetchEmployeesByEmail(String email){
        return this.employeeRepository.findByEmail(email);
    }
    public List<Employee> fetchEmployeesByName(String name){
        return this.employeeRepository.findByNameLike(name);
    }
    public Set<Employee> fetchEmployeesBySalary(double min, double max){
        return this.employeeRepository.findBySalaryBetween(min, max);
    }

    public void deleteEmployeeById(long employeeId){
        this.employeeRepository.deleteById(employeeId);
    }
}
