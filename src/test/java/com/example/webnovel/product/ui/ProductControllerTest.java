package com.example.webnovel.product.ui;

import com.example.webnovel.product.application.ProductService;
import com.example.webnovel.product.application.factory.ProductServiceFactory;
import com.example.webnovel.product.domain.type.ProductStatus;
import com.example.webnovel.product.domain.type.ProductType;
import com.example.webnovel.product.dto.ProductCreateRequest;
import com.example.webnovel.product.dto.ProductStatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductServiceFactory productServiceFactory;

    @MockBean
    private ProductService productService;

    @DisplayName("상품을 생성한다. (티켓 상품)")
    @WithMockUser
    @Test
    void createProduct() throws Exception {
        // given
        given(productServiceFactory.getProductService(any(ProductType.class))).willReturn(productService);
        ProductCreateRequest request = new ProductCreateRequest("test", 1000, 10, ProductType.TICKET);
        given(productService.createProduct(anyString(), anyInt(), anyInt())).willReturn(1L);

        // when & then
        mvc.perform(post("/api/v1/products")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/products/1"));
    }

    @DisplayName("상품의 상태를 변경한다. (티켓 상품)")
    @WithMockUser
    @Test
    void change_status() throws Exception {
        // given
        given(productServiceFactory.getProductService(any(ProductType.class))).willReturn(productService);
        final ProductStatusRequest productStatusRequest = new ProductStatusRequest(ProductStatus.SELLING, ProductType.TICKET);

        // when & then
        mvc.perform(patch("/api/v1/products/1/status")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productStatusRequest)))
                .andExpect(status().isOk());
    }
}
