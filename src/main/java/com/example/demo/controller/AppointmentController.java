package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/* Rest controller for appointments */
@RestController
public class AppointmentController {
    private AppointmentService appointmentService;

    /* Constructor */
    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    /* Post request (create appointment) */
    @PostMapping(value = "/appointment")
    public ResponseEntity<?> create(@RequestBody Appointment appointment){
        appointmentService.create(appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Put request (update appointment) */
    @PutMapping("/appointment/{id}")
    public ResponseEntity<List<Appointment>> update(@RequestBody Appointment appointment) {
        final List<Appointment> appointmentList = appointmentService.update(appointment);

        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    /* Get request (find all appointments) */
    @GetMapping(value = "/appointment")
    public ResponseEntity<List<Appointment>> findAll(){
        final List<Appointment> appointmentList = appointmentService.findAll();

        return appointmentList != null && !appointmentList.isEmpty()
                ? new ResponseEntity<>(appointmentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Get request (find appointment by id) */
    @GetMapping(value = "/appointment/{id}")
    public ResponseEntity<Optional<Appointment>> find(@PathVariable(name="id") Long id){
        final Optional<Appointment> appointment = appointmentService.find(id);

        return appointment != null
                ? new ResponseEntity<>(appointment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Delete request (delete appointment) */
    @DeleteMapping(value = "/appointment")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
