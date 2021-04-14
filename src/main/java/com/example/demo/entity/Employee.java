package com.example.demo.entity;

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

    @ManyToMany
    @JoinTable (name="employee_category",
            joinColumns=@JoinColumn (name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private List<Category> categoryList;
}