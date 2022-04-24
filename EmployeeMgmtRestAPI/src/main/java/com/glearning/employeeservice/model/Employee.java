package com.glearning.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long empId;

    @NotEmpty(message = "employee name cannot be empty")
    private String name;

    @Email(message = "not a valid email address")
    private String email;

    @Min(value = 40000, message = "salary should be min of 40000")
    @Max(value = 80000, message = "salary cannot exceed 60000")
    private double salary;

    @PastOrPresent(message = "DOB cannot be in the future")
    private LocalDate dob;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Dependent> dependents;

    public void addDependent(Dependent dependent){
        if (this.dependents == null){
            this.dependents = new HashSet<>();
        }
        this.dependents.add(dependent);
        dependent.setEmployee(this);
    }
}
