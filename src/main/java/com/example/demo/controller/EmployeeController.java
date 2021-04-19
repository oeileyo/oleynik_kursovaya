package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<?> create(@RequestBody Employee employee){
        employeeService.create(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/employee/update")
    public ResponseEntity<List<Employee>> update(@RequestBody Employee employee) {
        final List<Employee> employeeList = employeeService.update(employee);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<Employee>> findAll(){
        final List<Employee> employeeList = employeeService.findAll();

        return employeeList != null && !employeeList.isEmpty()
                ? new ResponseEntity<>(employeeList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<Employee> find(@PathVariable(name="id") Long id){
        final Employee employee = employeeService.find(id);

        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/employee")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
