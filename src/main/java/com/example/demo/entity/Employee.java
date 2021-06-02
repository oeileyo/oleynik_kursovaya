package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 *  Entity class for table employees
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    /* Parameters and joins */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    public Employee(String first_name) {
        this.first_name = first_name;
    }

    @OneToMany (mappedBy="employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointmentList;

    /* Getters */
    public Long getId() {return id;};

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    /* Setters */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

}
