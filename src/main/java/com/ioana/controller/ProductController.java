package com.ioana.controller;


import com.ioana.mapper.Mapper;
import com.ioana.model.Product;
import com.ioana.model.dto.ProductDtoRequest;
import com.ioana.model.dto.ProductDtoResponse;
import com.ioana.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
public class ProductController {

    private final ProductService productService;
    private final Mapper<Product, ProductDtoRequest> productMapper;
    private final Mapper<Product, ProductDtoResponse> productResponseMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDtoResponse>> getAllProducts() {
        log.info("productsList = {}", productService.getAll());

        return new ResponseEntity<>(productResponseMapper.convertModelsToDtos(productService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/add/product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        log.info("productDtoRequest = {}", productDtoRequest);

        return new ResponseEntity<>(productService.addProduct(productMapper.convertDtoToModel(productDtoRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {
        log.info("product = {}", productService.getProduct(id));

        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity<Product> changePrice(@RequestParam Long id, @RequestParam float newPrice) {
        log.info("product = {}", productService.changePriceForProduct(id, newPrice));

        return new ResponseEntity<>(productService.changePriceForProduct(id, newPrice), HttpStatus.OK);
    }
}
