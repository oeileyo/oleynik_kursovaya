package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  Employee Service
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *  Employee creation method
     * @param employee новый сотрудник
     */
    public void create(Employee employee){
        employeeRepository.save(employee);
    }

    /**
     *  Employee update method
     * @param employee новые данные сотрудника
     * @return список всех сотрудников
     */
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

    /**
     *  Find all employees method
     * @return список всех сотрудников
     */
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    /**
     *  Find employee by id method
     * @param id ID сотрудника
     * @return сотрудник
     */
    public Optional<Employee> find(Long id){
        return employeeRepository.findById(id);
    }

    /**
     *  Find employee by name method
     * @param name имя и фамилия сотрудника
     * @return сотрудник
     */
    public Long findByName(String name){
        String[] words = name.split("_");
        String first_name = words[0];
        String last_name = words[1];
        List<Employee> employeeList = findAll();
        for (Employee employee : employeeList){
            if (employee.getFirst_name().equals(first_name)  && employee.getLast_name().equals(last_name) ){
                return employee.getId();
            }
        }
        return null;
    }

    /**
     *  Delete employee by id method
     * @param id ID сотрудника
     */
    public void delete(Long id){ employeeRepository.deleteById(id); }
}
