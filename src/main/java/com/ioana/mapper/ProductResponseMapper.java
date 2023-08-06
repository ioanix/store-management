package com.ioana.mapper;

import com.ioana.model.Product;
import com.ioana.model.dto.ProductDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper extends AbstractMapper<Product, ProductDtoResponse> {

    @Override
    public Product convertDtoToModel(ProductDtoResponse productDtoResponse) {
        return new Product(productDtoResponse.getDateAdded(), productDtoResponse.getLastModified(), productDtoResponse.getName(), productDtoResponse.getPrice());
    }

    @Override
    public ProductDtoResponse convertModelToDto(Product product) {
        return new ProductDtoResponse(product.getDateAdded(), product.getLastModified(), product.getName(), product.getPrice());
    }
}
