package tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CartControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void addProductToCart() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String uuid = result.getResponse().getContentAsString().replaceAll("\"", "");

        mvc.perform(get("/api/v1/cart/" + uuid + "/add/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        mvc.perform(get("/api/v1/cart/" + uuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items", hasSize(1)))
                .andExpect(jsonPath("$.items[0].productTitle", is("Boots")))
                .andExpect(jsonPath("$.totalPrice", is(5499)));
    }
}