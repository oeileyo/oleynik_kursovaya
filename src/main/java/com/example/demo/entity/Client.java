package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/* Entity class for table clients */
@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    /* Parameters and joins */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToMany (mappedBy="client", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;

    /* Getters */
    public String getName() { return name; }

    public List<Appointment> getAppointmentList() { return appointmentList; }

    public String getPhone() { return phone; }

    /* Setters */
    public void setPhone(String phone) { this.phone = phone; }

    public void setName(String name) { this.name = name; }

    public void setAppointmentList(List<Appointment> appointmentList) { this.appointmentList = appointmentList; }
}