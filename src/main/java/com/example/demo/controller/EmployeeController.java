package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 *  Rest controller for employees
 */
@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    /**
     *  Constructor
     * @param employeeService сервис
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    /**
     *  Post request (create employee)
     * @param employee сотрудник
     * @return HttpStatus
     */
    @PostMapping("/employees")
    public ResponseEntity<?> create(@RequestBody Employee employee){
        employeeService.create(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     *  Put request (update employee)
     * @param employee сотрудник
     * @return HttpStatus
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<List<Employee>> update(@RequestBody Employee employee) {
        final List<Employee> employeeList = employeeService.update(employee);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    /**
     *  Get request (find all employees)
     * @return HttpStatus
     */
    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> findAll(){
        final List<Employee> employeeList = employeeService.findAll();

        return employeeList != null && !employeeList.isEmpty()
                ? new ResponseEntity<>(employeeList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Get request (find employee by id)
     * @param id ID сотрудника
     * @return HttpStatus
     */
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Optional<Employee>> find(@PathVariable(name="id") Long id){
        final Optional<Employee> employee = employeeService.find(id);

        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Get request (find employee by name)
     * @param name имя и фамилия сотрудника
     * @return HttpStatus
     */
    @GetMapping(value = "/employees/name/{name}")
    public ResponseEntity<Long> findByName(@PathVariable(name="name") String name){
        final Long employee_id = employeeService.findByName(name);

        return employee_id != null
                ? new ResponseEntity<>(employee_id, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Delete request (delete employee by id)
     * @param id ID сотрудника
     * @return HttpStatus
     */
    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
