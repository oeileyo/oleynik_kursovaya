package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 *  Rest controller for categories
 */
@RestController
public class CategoryController {
    private CategoryService categoryService;

    /**
     *  Constructor
     * @param categoryService сервис
     */
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    /**
     *  Post request (create category)
     * @param category категория
     * @return HttpStatus
     */
    @PostMapping(value = "/category")
    public ResponseEntity<?> create(@RequestBody Category category){
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     *  Put request (update category)
     * @param category категория
     * @return HttpStatus
     */
    @PutMapping("/category/{id}")
    public ResponseEntity<List<Category>> update(@RequestBody Category category) {
        final List<Category> categoryList = categoryService.update(category);

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /**
     *  Get request (find all categories)
     * @return HttpStatus
     */
    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> findAll(){
        final List<Category> categoryList = categoryService.findAll();

        return categoryList != null && !categoryList.isEmpty()
                ? new ResponseEntity<>(categoryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Get request (find category by id)
     * @param id ID категории
     * @return HttpStatus
     */
    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Optional<Category>> find(@PathVariable(name="id") Long id){
        final Optional<Category> category = categoryService.find(id);

        return category.isPresent()
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Get request (find category by name)
     * @param name имя категории
     * @return HttpStatus
     */
    @GetMapping(value = "/category/name/{name}")
    public ResponseEntity<Long> findByName(@PathVariable(name="name") String name){
        final Long category_id = categoryService.findByName(name);

        return category_id != null
                ? new ResponseEntity<>(category_id, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *  Delete request (delete category by id)
     * @param id ID категории
     * @return HttpStatus
     */
    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
