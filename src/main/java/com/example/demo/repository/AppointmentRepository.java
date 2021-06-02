package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Appointment Repository */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsById(Integer id);
    Boolean existsByDate(String date);
}
