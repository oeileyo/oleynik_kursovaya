package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<?> create(@RequestBody Category category){
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<List<Category>> update(@RequestBody Category category) {
        final List<Category> categoryList = categoryService.update(category);

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> findAll(){
        final List<Category> categoryList = categoryService.findAll();

        return categoryList != null && !categoryList.isEmpty()
                ? new ResponseEntity<>(categoryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Optional<Category>> find(@PathVariable(name="id") Long id){
        final Optional<Category> category = categoryService.find(id);

        return category.isPresent()
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
