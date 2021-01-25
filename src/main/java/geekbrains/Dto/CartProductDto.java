package geekbrains.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CartProductDto {
    private Long id;
    private String title;
    private int cost;
    private int count = 1;
}
