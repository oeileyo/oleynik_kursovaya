package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping(value = "/appointment")
    public ResponseEntity<?> create(@RequestBody Appointment appointment){
        appointmentService.create(appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/appointment/update")
    public ResponseEntity<List<Appointment>> update(@RequestBody Appointment appointment) {
        final List<Appointment> appointmentList = appointmentService.update(appointment);

        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @GetMapping(value = "/appointment")
    public ResponseEntity<List<Appointment>> findAll(){
        final List<Appointment> appointmentList = appointmentService.findAll();

        return appointmentList != null && !appointmentList.isEmpty()
                ? new ResponseEntity<>(appointmentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/appointment/{id}")
    public ResponseEntity<Appointment> find(@PathVariable(name="id") Long id){
        final Appointment appointment = appointmentService.find(id);

        return appointment != null
                ? new ResponseEntity<>(appointment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/appointment")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
