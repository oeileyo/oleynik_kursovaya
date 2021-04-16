package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void create(Employee employee){
        employeeRepository.save(employee);
    }

    public void add_category(Employee employee, Category category) {
        employee.getCategoryList().add(category);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee find(Long id){
        return employeeRepository.getOne(id);
    }

    public void delete(Long id){ employeeRepository.deleteById(id); }
}
