package com.example.demo.repository;

import com.example.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Client Repository */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Boolean existsById(Integer id);
    Boolean existsByName(String name);
}
