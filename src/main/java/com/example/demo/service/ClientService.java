package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void create(Client client){
        clientRepository.save(client);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client find(Long id){
        return clientRepository.getOne(id);
    }

    public void delete(Long id){ clientRepository.deleteById(id); }
}
