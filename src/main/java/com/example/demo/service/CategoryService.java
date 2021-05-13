package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  Category Service
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     *  Category creation method
     * @param category новая категория
     */
    public void create(Category category){ categoryRepository.save(category); }

    /**
     *  Category update method
     * @param category новые данные категории
     * @return список всех категорий
     */
    public List<Category> update(Category category) {
        var updatedCategory = categoryRepository.findById(category.getId());

        if (updatedCategory.isPresent()) {
            var _updatedCategory = updatedCategory.get();

            _updatedCategory.setName(category.getName() != null
                    ? category.getName() : _updatedCategory.getName());

            _updatedCategory.setPrice(category.getPrice() != null
                    ? category.getPrice() : _updatedCategory.getPrice());

            categoryRepository.save(_updatedCategory);
        }

        return categoryRepository.findAll();
    }

    /**
     *  Find all categories method
     * @return список всех категорий
     */
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    /**
     *  Find category by id method
     * @param id ID категории
     * @return список всех категорий
     */
    public Optional<Category> find(Long id){ return categoryRepository.findById(id); }

    /**
     *  Find category by name method
     * @param name имя категории
     * @return категория
     */
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

    /**
     *  Delete category by id method
     * @param id ID категории
     */
    public void delete(Long id){ categoryRepository.deleteById(id); }
}
