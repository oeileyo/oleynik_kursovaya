package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void create(Category category){
        categoryRepository.save(category);
    }

    public void add_employee(Category category, Employee employee) {
        category.getEmployeeList().add(employee);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category find(Long id){
        return categoryRepository.getOne(id);
    }

    public void delete(Long id){ categoryRepository.deleteById(id); }
}
