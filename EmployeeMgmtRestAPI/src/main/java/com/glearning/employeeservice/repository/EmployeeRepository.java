package com.glearning.employeeservice.repository;

import com.glearning.employeeservice.model.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameLike(String name);

    List<Employee>findByEmailLike(String email);

    Optional<Employee> findByNameAndEmail(String name, String email);

    List<Employee> findEmployeesByDobBetween(LocalDate startDate, LocalDate endDate);

    @Query("select employee from Employee employee where employee.email = ?1")
    Employee findByEmail(String emailAddress);

    Set<Employee> findBySalaryBetween(double min, double max);
    Set<Employee> findBySalaryBetween(double min, double max, PageRequest pageRequest);

}
