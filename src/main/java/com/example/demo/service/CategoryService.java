package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void create(Category category){ categoryRepository.save(category); }

    public List<Category> update(Category category) {
        var updatedCategory = categoryRepository.findById(category.getId());

        if (updatedCategory.isPresent()) {
            var _updatedCategory = updatedCategory.get();

            _updatedCategory.setName(category.getName() != null
                    ? category.getName() : _updatedCategory.getName());

            _updatedCategory.setPrice(category.getPrice() != null
                    ? category.getPrice() : _updatedCategory.getPrice());

//            _updatedCategory.setAppointmentList(category.getAppointmentList() != null
//                    ? category.getAppointmentList() : _updatedCategory.getAppointmentList());

            categoryRepository.save(_updatedCategory);
        }

        return categoryRepository.findAll();
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> find(Long id){ return categoryRepository.findById(id); }

    public Long findByName(String name){
        name = name.replaceAll("_", " ");
        List<Category> categoryList = findAll();
        for (Category category : categoryList){
            if (category.getName().equals(name)){
                return category.getId();
            }
        }
        return null;
    }

    public void delete(Long id){ categoryRepository.deleteById(id); }
}
