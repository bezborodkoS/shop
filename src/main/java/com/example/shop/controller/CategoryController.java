package com.example.shop.controller;

import com.example.shop.model.enity.Category;
import com.example.shop.service.crudMethodsForWorkModelDb.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//  TODO Using only ROLE_ADMIN
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{nameCategory}")
    public ResponseEntity<Optional<Category>> getCategoryByName(@PathVariable String nameCategory) {
        return categoryService.getByName(nameCategory).isPresent()
                ? new ResponseEntity<>(categoryService.getByName(nameCategory), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        return categoryService.save(category) != null
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>("this category " + category.getName() + " is present", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{nameCategory}")
    public ResponseEntity<String> deleteCategory(@PathVariable String nameCategory) {
        return categoryService.delete(nameCategory)
                ? new ResponseEntity<>("Delete category " + nameCategory, HttpStatus.OK)
                : new ResponseEntity<>("Cant found category " + nameCategory, HttpStatus.BAD_REQUEST);
    }

//    @DeleteMapping("/delete/{idCategory}")
//    public ResponseEntity<String> deleteCategory(@PathVariable Long idCategory) {
//        return categoryService.delete(idCategory)
//                ? new ResponseEntity<>("Delete category " + idCategory, HttpStatus.OK)
//                : new ResponseEntity<>("Cant found category with id " + idCategory, HttpStatus.BAD_REQUEST);
//    }

//    TODO dont work @RequestParam. Rewrite
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteCategory(@RequestParam("name") String name) {
//        System.out.println(name +" //////////");
//        return categoryService.delete(name)
//                ? new ResponseEntity<>("Delete category " + name, HttpStatus.OK)
//                : new ResponseEntity<>("Cant found category " + name, HttpStatus.BAD_REQUEST);
//    }
}