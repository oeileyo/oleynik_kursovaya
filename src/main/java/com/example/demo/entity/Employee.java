package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
//    private Boolean is_active; // работает ли

    @OneToMany (mappedBy="employee", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;

    @ManyToMany(mappedBy = "employeeList", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Category> categoryList;

}