package com.ioana.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioana.model.dto.ProductDtoRequest;
import com.ioana.repository.AppUserRepository;
import com.ioana.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    @WithUserDetails("testadmin")
    void shouldShowAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("testadmin")
    void shouldSaveAProduct() throws Exception {
        mockMvc.perform(post("/add/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductDtoRequest("name", 1.0f))))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("testadmin")
    void shouldFindAProductById() throws Exception {
        MvcResult result = mockMvc.perform(get("/product/{id}", 13L))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(String.valueOf(13), result.getResponse().getContentAsString().substring(6, 8));
    }

    @Test
    @WithUserDetails("testadmin")
    void shouldChangePriceForProduct() throws Exception {
        MvcResult result = mockMvc.perform(put("/product/update?id={id}&newPrice={price}", 2, 170))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("\"price\":170.0"));
    }

    @Test
    @WithUserDetails("johndoe")
    void shouldThrowExceptionIfUserIsNotAuthorizedToSaveAProduct() throws Exception {
        mockMvc.perform(post("/add/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ProductDtoRequest("name", 1.0f))))
                .andExpect(status().isForbidden());
    }
}