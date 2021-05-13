package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 *  Entity class for table categories
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    /* Parameters and joins */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    @OneToMany (mappedBy="category", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Appointment> appointmentList;


    /* Getters */
    public String getName() { return name; }

    public Integer getPrice() { return price; }

    public List<Appointment> getAppointmentList() { return appointmentList; }

    /* Setters */
    public void setName(String name) { this.name = name; }

    public void setPrice(Integer price) { this.price = price; }

    public void setAppointmentList(List<Appointment> appointmentList) { this.appointmentList = appointmentList; }
}
