package tests;

import geekbrains.Dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import geekbrains.Dto.ProductDto;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<ProductDto> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Tomato");
        productDto.setCost(1200);

        assertThat(jackson.write(productDto))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("Tomato");
        assertThat(jackson.write(productDto))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathNumberValue("$.price").isEqualTo(1200);
    }
}