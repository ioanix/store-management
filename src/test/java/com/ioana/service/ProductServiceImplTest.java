package com.ioana.service;

import com.ioana.exception.ItemNotFoundException;
import com.ioana.model.Product;
import com.ioana.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    private static final String PRODUCT_NAME = "product name";
    private static final Long PRODUCT_ID = 1L;
    private static final float PRODUCT_PRICE = 1.0f;
    private static final float NEW_PRICE = 2.0f;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    private ProductServiceImpl underTest;


    @BeforeEach
    void setup() {
        underTest = new ProductServiceImpl(productRepository);
        product = new Product(PRODUCT_NAME, PRODUCT_PRICE);
    }

    @Test
    void shouldFindAllProducts() {
        underTest.getAll();

        verify(productRepository).findAll();
    }

    @Test
    void shouldSaveAProduct() {
        underTest.addProduct(product);

        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);

        verify(productRepository)
                .save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void shouldGetAProjectById() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        assertThat(underTest.getProduct(PRODUCT_ID)).isEqualTo(product);
    }

    @Test
    void shouldThrowExceptionIfProductIsNotFoundInTheDB() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getProduct(PRODUCT_ID))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("The product does not exist");
    }

    @Test
    void shouldChangePriceForProject() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        underTest.changePriceForProduct(PRODUCT_ID, NEW_PRICE);

        verify(productRepository).save(product);
        assertThat(product.getPrice()).isEqualTo(NEW_PRICE);
    }

    @Test
    void shouldThrowExceptionIfProductIsNotFound() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.changePriceForProduct(PRODUCT_ID, NEW_PRICE))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("The product does not exist");
    }
}