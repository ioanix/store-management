package com.ioana.controller;


import com.ioana.model.Product;
import com.ioana.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {

        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity<Product> changePrice(@RequestParam Long id, @RequestParam float newPrice) {
        return new ResponseEntity<>(productService.changePriceForProduct(id, newPrice), HttpStatus.OK);
    }
}
