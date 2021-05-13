package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 *  Rest controller for clients
 */
@RestController
public class ClientController {
    private ClientService clientService;

    /**
     *  Constructor
     * @param clientService сервис
     */
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    /**
     *  Post request (create client)
     * @param client клиент
     * @return HttpStatus
     */
    @PostMapping(value = "/client")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     *  Get request (find all clients)
     * @return HttpStatus
     */
    @GetMapping(value = "/client")
    public ResponseEntity<List<Client>> findAll(){
        final List<Client> clientList = clientService.findAll();

        return clientList != null && !clientList.isEmpty()
                ? new ResponseEntity<>(clientList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Get request (find client by id)
     * @param id ID клиента
     * @return HttpStatus
     */
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Optional<Client>> find(@PathVariable(name="id") Long id){
        final Optional<Client> client = clientService.find(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Delete request (delete client by id)
     * @param id ID клиента
     * @return HttpStatus
     */
    @DeleteMapping(value = "/client")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
