package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Category;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void create(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public List<Appointment> update(Appointment appointment) {
        var updatedAppointment = appointmentRepository.findById(appointment.getId());

        if (updatedAppointment.isPresent()) {
            var _updatedAppointment = updatedAppointment.get();

            _updatedAppointment.setDate_time(appointment.getDate_time() != null
                    ? appointment.getDate_time() : _updatedAppointment.getDate_time());

            _updatedAppointment.setCategory(appointment.getCategory() != null
                    ? appointment.getCategory() : _updatedAppointment.getCategory());

            _updatedAppointment.setEmployee(appointment.getEmployee() != null
                    ? appointment.getEmployee() : _updatedAppointment.getEmployee());

            _updatedAppointment.setClient(appointment.getClient() != null
                    ? appointment.getClient() : _updatedAppointment.getClient());

            _updatedAppointment.setStatus(appointment.getStatus() != null
                    ? appointment.getStatus() : _updatedAppointment.getStatus());

            appointmentRepository.save(_updatedAppointment);
        }

        return appointmentRepository.findAll();
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment find(Long id){
        return appointmentRepository.getOne(id);
    }

    public void delete(Long id){ appointmentRepository.deleteById(id); }
}