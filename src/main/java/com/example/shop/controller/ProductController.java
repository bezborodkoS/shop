package com.example.shop.controller;

import com.example.shop.model.enity.Product;
import com.example.shop.service.crudMethodsForWorkModelDb.CategoryService;
import com.example.shop.service.crudMethodsForWorkModelDb.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    //    TODO May be delete because in ProductService have this method
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getAllProductByCategoryName(@PathVariable String categoryName) {

        return new ResponseEntity<>(productService.getAllByCategoryName(categoryName), HttpStatus.OK);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Optional<Product>> getProductByProductCode(@PathVariable String productCode) {
        return productService.getByProductCode(productCode).isPresent()
                ? new ResponseEntity<>(productService.getByProductCode(productCode), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create/{nameCategory}")
    public ResponseEntity<String> createProduct(@RequestBody Product product,
                                                @PathVariable String nameCategory) {
        if (productService.getByNameAndFabricator(product.getName(), product.getFabricator()).isPresent()) {
            return new ResponseEntity<>("This product is in the store", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return productService.save(product, nameCategory) != null
                ? new ResponseEntity<>("Product add to shop", HttpStatus.CREATED)
                : new ResponseEntity<>("This category is not available in the store", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<String> deleteCategory(@PathVariable String productCode) {
        return productService.delete(productCode)
                ? new ResponseEntity<>("Delete product with code " + productCode, HttpStatus.OK)
                : new ResponseEntity<>("Cant found product with code " + productCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
