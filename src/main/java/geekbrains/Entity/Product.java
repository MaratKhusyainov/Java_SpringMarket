package geekbrains.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import geekbrains.Dto.ProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "products_tbl")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "cost_fld")
    private int cost;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product(ProductDto p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.cost = p.getCost();
    }
}