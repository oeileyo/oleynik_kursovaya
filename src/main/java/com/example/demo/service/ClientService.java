package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  Client Service
 */
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Client creation method
     * @param client новый клиент
     */
    public void create(Client client){
        clientRepository.save(client);
    }

    /**
     * Find all clients method
     * @return список клиентов
     */
    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    /**
     * Find all clients method
     * @param id ID клиента
     * @return клиент
     */
    public Optional<Client> find(Long id){
        return clientRepository.findById(id);
    }

    /**
     * Delete client by id method
     * @param id ID клиента
     */
    public void delete(Long id){ clientRepository.deleteById(id); }
}
