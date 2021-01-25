package geekbrains.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Cart {
    private List<CartProductDto> products = new ArrayList<>();
}
