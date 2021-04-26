package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void create(Employee employee){
        employeeRepository.save(employee);
    }


  public List<Employee> update(Employee employee) {
      var updatedEmployee = employeeRepository.findById(employee.getId());

      if (updatedEmployee.isPresent()) {
          var _updatedEmployee = updatedEmployee.get();

          _updatedEmployee.setFirst_name(employee.getFirst_name() != null
                  ? employee.getFirst_name() : _updatedEmployee.getFirst_name());

          _updatedEmployee.setLast_name(employee.getLast_name() != null
                  ? employee.getLast_name() : _updatedEmployee.getLast_name());

          _updatedEmployee.setAppointmentList(employee.getAppointmentList() != null
                  ? employee.getAppointmentList() : _updatedEmployee.getAppointmentList());

          employeeRepository.save(_updatedEmployee);
      }

      return employeeRepository.findAll();
  }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> find(Long id){
        return employeeRepository.findById(id);
    }

    public void delete(Long id){ employeeRepository.deleteById(id); }
}
