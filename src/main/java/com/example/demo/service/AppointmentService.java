package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Category;
import com.example.demo.entity.Client;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  Appointment Service
 */
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *  Appointment creation method
     * @param appointment запись
     */
    public void create(Appointment appointment){
        Category category = categoryRepository.findById(appointment.getCategory_id()).orElseThrow();
        appointment.setCategory(category);
        Client client = clientRepository.findById(appointment.getClient_id()).orElseThrow();
        appointment.setClient(client);
        Employee employee = employeeRepository.findById(appointment.getEmployee_id()).orElseThrow();
        appointment.setEmployee(employee);
        appointmentRepository.save(appointment);
    }

    /**
     *  Appointment update method
     * @param appointment новые данные записи
     * @return список всех записей
     */
    public List<Appointment> update(Appointment appointment) {
        var updatedAppointment = appointmentRepository.findById(appointment.getId());

        if (updatedAppointment.isPresent()) {
            var _updatedAppointment = updatedAppointment.get();

            _updatedAppointment.setDate(appointment.getDate() != null
                    ? appointment.getDate() : _updatedAppointment.getDate());

            _updatedAppointment.setTime(appointment.getTime() != null
                    ? appointment.getTime() : _updatedAppointment.getTime());

            _updatedAppointment.setCategory(appointment.getCategory() != null
                    ? appointment.getCategory() : _updatedAppointment.getCategory());

            _updatedAppointment.setEmployee(appointment.getEmployee() != null
                    ? appointment.getEmployee() : _updatedAppointment.getEmployee());

            _updatedAppointment.setClient(appointment.getClient() != null
                    ? appointment.getClient() : _updatedAppointment.getClient());

            appointmentRepository.save(_updatedAppointment);
        }

        return appointmentRepository.findAll();
    }

    /**
     *  Find all appointments method
     * @return список всех записей
     */
    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    /**
     *  Find appointment by id method
     * @param id ID записи
     * @return запись
     */
    public Optional<Appointment> find(Long id){ return appointmentRepository.findById(id); }

    /**
     *  Delete appointment by id method
     * @param id ID записи
     */
    public void delete(Long id){ appointmentRepository.deleteById(id); }
}