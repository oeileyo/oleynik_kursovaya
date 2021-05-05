package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToMany (mappedBy="client", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public List<Appointment> getAppointmentList() { return appointmentList; }

    public void setAppointmentList(List<Appointment> appointmentList) { this.appointmentList = appointmentList; }
}