package com.ioana.service;

import com.ioana.exception.ItemNotFoundException;
import com.ioana.model.Product;
import com.ioana.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("The product does not exist"));
    }

    @Override
    public Product changePriceForProduct(Long id, float newPrice) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) {
            throw new ItemNotFoundException("The product does not exist");
        }

        Product existingProduct = productOptional.get();
        existingProduct.setPrice(newPrice);

        productRepository.save(existingProduct);

        return existingProduct;
    }
}
