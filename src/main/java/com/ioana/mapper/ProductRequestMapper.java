package com.ioana.mapper;

import com.ioana.model.Product;
import com.ioana.model.dto.ProductDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper extends AbstractMapper<Product, ProductDtoRequest> {

    @Override
    public Product convertDtoToModel(ProductDtoRequest productDtoRequest) {
        return new Product(productDtoRequest.getName(), productDtoRequest.getPrice());
    }

    @Override
    public ProductDtoRequest convertModelToDto(Product product) {
        return new ProductDtoRequest(product.getName(), product.getPrice());
    }
}
