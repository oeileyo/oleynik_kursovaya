package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    @ManyToMany
    @JoinTable (name="employee_category",
            joinColumns=@JoinColumn (name="category_id"),
            inverseJoinColumns=@JoinColumn(name="employee_id"))
    private List<Employee> employeeList;

    @OneToMany (mappedBy="category", cascade = CascadeType.PERSIST)
    private List<Appointment> appointmentList;
}
