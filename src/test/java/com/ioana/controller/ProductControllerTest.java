package com.ioana.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioana.model.dto.ProductDtoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldShowAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveAProduct() throws Exception {
        mockMvc.perform(post("/add/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductDtoRequest("name", 1.0f))))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindAProductById() throws Exception {
        MvcResult result = mockMvc.perform(get("/product/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(String.valueOf(1), result.getResponse().getContentAsString().substring(6, 7));
    }

    @Test
    void shouldChangePriceForProduct() throws Exception {
        MvcResult result = mockMvc.perform(put("/product/update?id={id}&newPrice={price}", 1, 7))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("\"price\":7.0"));
    }
}