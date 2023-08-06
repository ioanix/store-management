package com.ioana.service;

import com.ioana.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product addProduct(Product product);

    Product getProduct(Long id);

    Product changePriceForProduct(Long id, float newPrice);
}
