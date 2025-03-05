package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.service.crudMethodsForWorkModelDb.CategoryService;
import com.example.shop.service.crudMethodsForWorkModelDb.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }



    @PostMapping("/create/{nameCategory}")
    public ResponseEntity<String> createProduct(@RequestBody Product product,
                                                @PathVariable String nameCategory) {
        if (productService.getByName(product.getName()).isPresent()) {
            return new ResponseEntity<>("This product is in the store", HttpStatus.BAD_REQUEST);
        }
        return productService.save(product, nameCategory) != null
                ? new ResponseEntity<>("Product add to shop", HttpStatus.CREATED)
                : new ResponseEntity<>("This category is not available in the store", HttpStatus.BAD_REQUEST);
    }

}
