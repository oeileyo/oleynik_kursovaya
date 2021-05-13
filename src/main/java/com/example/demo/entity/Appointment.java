package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 *  Entity class for table appointments
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    /* Parameters and joins */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String time;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
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

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="client_id")
    @JsonIgnore
    private Client client;
    @Column(insertable = false, updatable = false)
    private Long client_id;

    /* Getters */
    public String getDate() { return date; }

    public String getTime() { return time; }

    public Category getCategory() { return category; }

    public Long getCategory_id() { return category_id; }

    public Employee getEmployee() { return employee; }

    public Long getEmployee_id() { return employee_id; }

    public Client getClient() { return client; }

    public Long getClient_id() { return client_id; }

    /* Setters */
    public void setDate(String date) { this.date = date; }

    public void setTime(String time) { this.time = time; }

    public void setCategory(Category category) { this.category = category; }

    public void setCategory_id(Long category_id) { this.category_id = category_id; }

    public void setEmployee(Employee employee) { this.employee = employee; }

    public void setEmployee_id(Long employee_id) { this.employee_id = employee_id; }

    public void setClient(Client client) { this.client = client; }

    public void setClient_id(Long client_id) { this.client_id = client_id; }
}