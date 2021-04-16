package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date_time;
    private Boolean status; // available/unavailable

    @ManyToOne (optional = true, cascade = CascadeType.ALL)
    @JoinColumn (name = "category_id")
    @JsonIgnore
    private Category category;
    @Column(insertable = false, updatable = false)
    private Long category_id;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn (name = "employee_id")
    @JsonIgnore
    private Employee employee;
    @Column(insertable = false, updatable = false)
    private Long employee_id;

    @ManyToOne (optional=true, cascade=CascadeType.ALL)
    @JoinColumn (name="client_id")
    @JsonIgnore
    private Client client;
    @Column(insertable = false, updatable = false)
    private Long client_id;

}