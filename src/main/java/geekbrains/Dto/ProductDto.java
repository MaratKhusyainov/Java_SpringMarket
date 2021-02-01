package geekbrains.Dto;


import geekbrains.Entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String title;
    private int cost;

    public ProductDto(Product p){
        this.id = p.getId();
        this.title = p.getTitle();
        this.cost = p.getCost();
    }
}
