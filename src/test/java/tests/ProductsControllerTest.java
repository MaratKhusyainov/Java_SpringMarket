package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import geekbrains.Dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllProductsTest() throws Exception {
        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].title", is("Boots")));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        mvc.perform(get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Boots")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.price", is(5499)));
    }

    @Test
    public void saveProductTest() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setTitle("Apple");
        productDto.setCost(100);
        this.mvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteProductById() throws Exception {
        mvc.perform(delete("/api/v1/products/2")
                .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isOk());
    }
}