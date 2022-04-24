package com.glearning.employeeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@EqualsAndHashCode(exclude = "employee")
@ToString(exclude = "employee")
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="employee", nullable = false)
    private Employee employee;

}
